package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.FhirResourceLoader;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Controller
public class HospitalResultController {

    @Autowired
    FhirResourceLoader ddl;

    @RequestMapping("/hospitalResult")
    String index(@RequestParam(value = "hospital", required = false, defaultValue = "all") String[] hospitals, Model model) {
        ArrayList<HospitalResult> hospitalObs = new ArrayList<>();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("hospitalObs", hospitalObs);


        if (hospitals.length==0) {
            ddl.getObservations().forEach(obs -> {
                hospitalObs.add(new HospitalResult(obs));
            });
        } else {
            ddl.getObservations().forEach(obs -> {
                for (String hospital : hospitals) {
                    if (hospital.equals(obs.getPerformer())||hospital.equals("all")) {
                        hospitalObs.add(new HospitalResult(obs));
                    }
                }
            });
        }

        sortData(hospitalObs);

        StringBuffer dataSet = new StringBuffer();

        for(int i=0;i<hospitalObs.size();i++){
            dataSet.append(hospitalObs.get(i).getEffective()+","+hospitalObs.get(i).getTestItem()+","+(hospitalObs.get(i).getTestValue()+hospitalObs.get(i).getTestUnit())
            +","+hospitalObs.get(i).getStandards()+","+hospitalObs.get(i).getInterpretation()+","+hospitalObs.get(i).getPerfomer()+",");
        }

        model.addAttribute("dataSet",dataSet.toString());

        return "hospitalResult";
    }

    //SORT EFFECTIVE DATA
    ArrayList<HospitalResult> sortData(ArrayList<HospitalResult> hospitalObs){
        Collections.sort(hospitalObs, new Comparator<HospitalResult>() {
            @Override
            public int compare(HospitalResult o1, HospitalResult o2) {
                return o1.getEffective().compareTo(o2.getEffective());
            }
        });
        return hospitalObs;
    }

    @Data
    class HospitalResult {
        String effective;
        String testItem;
        String testValue;
        String testUnit;
        String standards;
        String interpretation;
        String perfomer;

        public HospitalResult() {
            this.effective = "";
            this.testItem = "";
            this.testValue = "";
            this.testUnit = "";
            this.standards = "";
            this.interpretation = "";
            this.perfomer = "";
        }

        HospitalResult(GFObservation obs) {
            this();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
            this.effective = dateFormat.format(obs.getEffective());
            this.testItem = obs.getCode().getName();
            this.testValue = Double.toString(obs.getValue());
            this.testUnit = obs.getUnit();
            this.standards = "";
            this.interpretation = obs.getInterpretation().getName();
            this.perfomer = obs.getPerformer();
        }

    }
}