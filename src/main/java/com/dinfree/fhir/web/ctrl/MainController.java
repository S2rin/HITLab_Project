package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.FhirResourceLoader;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import com.dinfree.fhir.web.domain.data.observation.LoincCode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by HITLab01 on 2016-08-11.
 */
@Controller
public class MainController {

    @Autowired
    FhirResourceLoader ddl;

    @RequestMapping("/")
    String loginlist(Model model){

        model.addAttribute("pat",ddl.getUser());
        return "login";
    }

    @RequestMapping("/main")
    String main(Model model){
        ArrayList<MainData> mainDataObs = new ArrayList<>();
        model.addAttribute("mainDataObs",mainDataObs);

        ArrayList<MainData> bpObs = new ArrayList<>();
        model.addAttribute("bpObs", bpObs);

        ArrayList<MainData> bsObs = new ArrayList<>();
        model.addAttribute("bsObs",bsObs);

        ArrayList<MainData> wtObs = new ArrayList<>();
        model.addAttribute("wtObs",wtObs);

        ArrayList<MainData> hrObs = new ArrayList<>();
        model.addAttribute("hrObs",hrObs);

        ArrayList<MainData> hosG = new ArrayList<>();
        model.addAttribute("hosG",hosG);

        ArrayList<MainData> hosSn = new ArrayList<>();
        model.addAttribute("hosSn",hosSn);

        ArrayList<MainData> hosS = new ArrayList<>();
        model.addAttribute("hosS",hosS);

        mainDataObs.clear();


        ddl.getObservations().forEach(obs -> {
            mainDataObs.add(new MainData(obs));
        });

        ddl.getObservations().forEach(obs -> {
            if(obs.getCode().equals(LoincCode.Weight)) {
                wtObs.add(new MainData(obs));
                int wtSize = wtObs.size();
            }
        });

        //SORT DATA
        sortData(wtObs);

        //Chart
        ArrayList<String> wtLabel = new ArrayList<>();
        ArrayList<String> wtValue = new ArrayList<>();

        wtLabel = chartLabel(wtObs);
        wtValue = chartValue(wtObs);

        model.addAttribute("wtlabel",wtLabel);
        model.addAttribute("wtvalue",wtValue);


        ddl.getObservations().forEach(obs -> {
            if(obs.getCode().equals(LoincCode.HeartRate)) {
                hrObs.add(new MainData(obs));
                int hrSize = hrObs.size();
            }

        });

        //SORT DATA
        sortData(hrObs);

        //Chart
        ArrayList<String> hrLabel = new ArrayList<>();
        ArrayList<String> hrValue = new ArrayList<>();

        hrLabel = chartLabel(hrObs);
        hrValue = chartValue(hrObs);

        model.addAttribute("hrlabel",hrLabel);
        model.addAttribute("hrvalue",hrValue);

        ddl.getObservations().forEach(obs -> {
            if(obs.getCode().equals(LoincCode.BloodPressure)) {
                bpObs.add(new MainData(obs));
                int bpSize = bpObs.size();
            }
        });

        //SORT DATA
        sortData(bpObs);

        //Chart
        ArrayList<String> bpLabel = new ArrayList<>();
        ArrayList<String> bpValue = new ArrayList<>();

        bpLabel = chartLabel(bpObs);
        bpValue = chartValue(bpObs);

        model.addAttribute("bplabel",bpLabel);
        model.addAttribute("bpvalue",bpValue);

        ddl.getObservations().forEach(obs -> {
            if(obs.getCode().equals(LoincCode.Glucose)) {
                bsObs.add(new MainData(obs));
                int bsSize = bsObs.size();
                int bs1 = bsSize - 1;
            }
        });

        //SORT DATA
        sortData(bsObs);

        //Chart
        ArrayList<String> bsLabel = new ArrayList<>();
        ArrayList<String> bsValue = new ArrayList<>();

        bsLabel = chartLabel(bsObs);
        bsValue = chartValue(bsObs);

        model.addAttribute("bslabel",bsLabel);
        model.addAttribute("bsvalue",bsValue);

        ddl.getObservations().forEach(obs -> {
            if((obs.getPerformer()).equals("길병원")) {
                hosG.add(new MainData(obs));
            }
        });

        ddl.getObservations().forEach(obs -> {
            if((obs.getPerformer()).equals("서울대병원")) {
                hosSn.add(new MainData(obs));
            }
        });

        ddl.getObservations().forEach(obs -> {
            if((obs.getPerformer()).equals("성모병원")) {
                hosS.add(new MainData(obs));
            }
        });

        return "main";

    }
    //CREATE CHARTLABEL
    ArrayList<String> chartLabel(ArrayList<MainData> mainDataObs){
        ArrayList<String> label = new ArrayList<>();
        for(int i=0;i<7;i++){
            label.add(mainDataObs.get(i).getEffective());
        }
        return label;
    }

    //CREATE CHARTVALUE
    ArrayList<String> chartValue(ArrayList<MainData> mainDataObs){
        ArrayList<String> value = new ArrayList<>();
        for(int i=0;i<7;i++){
            value.add(mainDataObs.get(i).getTestValue().toString());
        }
        return value;
    }

    //SORTING MainDataOBS ACCENDING
    ArrayList<MainData> sortData(ArrayList<MainData> MainDataObs){
        Collections.sort(MainDataObs, new Comparator<MainData>() {
            @Override
            public int compare(MainData o1, MainData o2) {
                return o1.getEffective().compareTo(o2.getEffective());
            }
        });
        return MainDataObs;
    }
    
    @Data
    private class MainData {
        String effective;
        String testItem;
        String testValue;
        String testUnit;
        String standards;
        String interpretation;
        String perfomer;
        int totalNum;

        public MainData(){
            this.effective = "";
            this.testItem = "";
            this.testValue = "";
            this.testUnit = "";
            this.standards = "";
            this.interpretation = "";
            this.perfomer = "";
            this.totalNum = 0;
        }

        public MainData(GFObservation obs){
            this();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
            this.effective = dateFormat.format(obs.getEffective());
            this.testItem = obs.getCode().getName();
            this.testValue = Double.toString(obs.getValue());
            this.testUnit = obs.getUnit();
            this.standards = "";
            this.interpretation = obs.getInterpretation().getName();
            this.perfomer = obs.getPerformer();
            this.totalNum = obs.getTotalNum();
        }
    }
}