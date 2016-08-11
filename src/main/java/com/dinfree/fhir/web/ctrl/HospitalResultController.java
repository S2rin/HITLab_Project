package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.DevDataLoader;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Controller
public class HospitalResultController {

    @Autowired DevDataLoader ddl;

    @RequestMapping("/hospitalResult")
    String index(@RequestParam(value = "hospital", required = false, defaultValue = "all")String hospital , Model model) {
        ArrayList<HospitalResult> hospitalObs = new ArrayList<>();
        model.addAttribute("hospital", hospital);
        model.addAttribute("hospitalObs", hospitalObs);

        if (hospital.equals("all")) {
            ddl.getObservations().forEach(obs -> {
                hospitalObs.add(new HospitalResult(obs));
            });
        } else {
            ddl.getObservations().forEach(obs -> {
                if (hospital.equals(obs.getManagingOrganization())) {
                    hospitalObs.add(new HospitalResult(obs));
                }
            });
        }
        

        return "hospitalResult";
    }

    @Data
    class HospitalResult {
        String effective;
        String testItem;
        String testValue;
        String testUnit;
        String standards;
        String interpretation;
        String managingOrganization;

        public HospitalResult() {
            this.effective = "";
            this.testItem = "";
            this.testValue = "";
            this.testUnit = "";
            this.standards = "";
            this.interpretation = "";
            this.managingOrganization = "";
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
            this.managingOrganization = obs.getManagingOrganization();
        }

    }
}