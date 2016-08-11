package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.DevDataLoader;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import com.dinfree.fhir.web.domain.data.observation.LoincCode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by hitlab on 2016. 8. 11..
 */

@Controller
public class HealthDataController {
    @Autowired DevDataLoader ddl;

    @RequestMapping("/weight")
    String weight(Model model){
        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);

        healthDataObs.clear();

        ddl.getObservations().forEach(obs ->{
            if(obs.getCode().equals(LoincCode.Weight)){
                healthDataObs.add(new HealthData (obs));
            }
        });


        return "weight";
    }

    @RequestMapping("/heartBeat")
    String heartbeat(Model model){
        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);

        healthDataObs.clear();

        ddl.getObservations().forEach(obs ->{
            if(obs.getCode().equals(LoincCode.HeartRate)){
                healthDataObs.add(new HealthData (obs));
            }
        });

        return "heartBeat";
    }

    @RequestMapping("/bloodPressure")
    String bloodPressure(Model model){
        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);

        healthDataObs.clear();

        ddl.getObservations().forEach(obs ->{
            if(obs.getCode().equals(LoincCode.BloodPressure)){
                healthDataObs.add(new HealthData (obs));
            }
        });

        return "bloodPressure";
    }

    @RequestMapping("/bloodSugar")
    String bloodSugar(Model model){
        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);

        healthDataObs.clear();

        ddl.getObservations().forEach(obs ->{
            if(obs.getCode().equals(LoincCode.Glucose)){
                healthDataObs.add(new HealthData (obs));
            }
        });
        return "bloodSugar";
    }

    @Data
    public class HealthData{
            String effective;
            String testItem;
            String testValue;
            String testUnit;
            String standards;
            String interpretation;
            String managingOrganization;
            String performer;

            public HealthData(){
                this.effective = "";
                this.testItem = "";
                this.testValue = "";
                this.testUnit = "";
                this.standards = "";
                this.interpretation = "";
                this.managingOrganization = "";
                this.performer = "";
            }

            public HealthData(GFObservation obs){
                this();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
                this.effective = dateFormat.format(obs.getEffective());
                this.testItem = obs.getCode().getName();
                this.testValue = Double.toString(obs.getValue());
                this.testUnit = obs.getUnit();
                this.standards = "";
                this.interpretation = obs.getInterpretation().getName();
                this.managingOrganization = obs.getManagingOrganization();
                this.performer = obs.getPerformer();
            }
    }

}

