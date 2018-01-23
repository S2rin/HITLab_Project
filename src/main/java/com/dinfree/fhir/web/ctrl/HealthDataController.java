package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.FhirResourceLoader;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import com.dinfree.fhir.web.domain.data.observation.LoincCode;
import lombok.Data;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hitlab on 2016. 8. 11..
 */

@Controller
public class HealthDataController {

    @Autowired
    FhirResourceLoader ddl;

    @RequestMapping("/weight")
    String weight(@RequestParam(value="startDate", required = false, defaultValue = "value1")String startDate,
                  @RequestParam(value="endDate", required = false, defaultValue = "value2") String endDate, Model model){

        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);
        healthDataObs.clear();

        if(startDate.equals("value1")&&endDate.equals("value2")){
            ddl.getObservations().forEach(obs ->{
                if(obs.getCode().equals(LoincCode.Weight)){
                    healthDataObs.add(new HealthData (obs));
                }
            });
            model.addAttribute("dateCheck","today");
        }else{
            //사용자가 설정한 date 기간의 값만 가져와서 출력
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date startValue = dateFormat.parse(startDate);
                Date endValue = dateFormat.parse(endDate);

                model.addAttribute("dateCheck","daterange");
                model.addAttribute("startDay",startDate);
                model.addAttribute("endDay",endDate);

                ddl.getObservations().forEach(obs ->{
                    if(obs.getCode().equals(LoincCode.Weight)){
                        if(((obs.getEffective()).compareTo(startValue)>=0)&&((obs.getEffective()).compareTo(endValue)<=0)){
                            healthDataObs.add(new HealthData(obs));
                        }
                    }
                });
            }catch (ParseException e){
                e.printStackTrace();
            }
        }

        //SORT HEALTHDATAOBS
        sortData(healthDataObs);

        //CREATE CHART DATA
        ArrayList<String> label = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();


        for(int i=0;i<healthDataObs.size();i++){
            label.add(healthDataObs.get(i).getEffective());
            value.add(healthDataObs.get(i).getTestValue().toString());
        }

        model.addAttribute("label",label);
        model.addAttribute("value",value);

        //DataTable
        StringBuffer dataSet = new StringBuffer();

        for(int i=0;i<healthDataObs.size();i++){
            dataSet.append(healthDataObs.get(i).getEffective()+","+(healthDataObs.get(i).getTestValue()+healthDataObs.get(i).getTestUnit())
                    +","+healthDataObs.get(i).getInterpretation()+","+healthDataObs.get(i).getPerfomer()+",");
        }

        model.addAttribute("dataSet",dataSet.toString());

        return "weight";
    }

    @RequestMapping(value = "/heartRate")
    String heartbeat(@RequestParam(value="startDate", required = false, defaultValue = "value1")String startDate,
                     @RequestParam(value="endDate", required = false, defaultValue = "value2") String endDate, Model model){

        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);

        healthDataObs.clear();

        if(startDate.equals("value1")&&endDate.equals("value2")){
            ddl.getObservations().forEach(obs ->{
                if(obs.getCode().equals(LoincCode.HeartRate)){
                    healthDataObs.add(new HealthData (obs));
                }
            });
            model.addAttribute("dateCheck","today");
        }else{
            //사용자가 설정한 date 기간의 값만 가져와서 출력
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date startValue = dateFormat.parse(startDate);
                Date endValue = dateFormat.parse(endDate);

                model.addAttribute("dateCheck","daterange");
                model.addAttribute("startDay",startDate);
                model.addAttribute("endDay",endDate);

                ddl.getObservations().forEach(obs ->{
                    if(obs.getCode().equals(LoincCode.HeartRate)){
                        if(((obs.getEffective()).compareTo(startValue)>=0)&&((obs.getEffective()).compareTo(endValue)<=0)){
                            healthDataObs.add(new HealthData(obs));
                        }
                    }
                });
            }catch (ParseException e){
                e.printStackTrace();
            }
        }

        //SORT HEALTHDATAOBS
        sortData(healthDataObs);

        //CREATE CHART
        ArrayList<String> label = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();


        for(int i=0;i<healthDataObs.size();i++){
            label.add(healthDataObs.get(i).getEffective());
            value.add(healthDataObs.get(i).getTestValue().toString());
        }

        model.addAttribute("label",label);
        model.addAttribute("value",value);

        //DataTable
        StringBuffer dataSet = new StringBuffer();

        for(int i=0;i<healthDataObs.size();i++){
            dataSet.append(healthDataObs.get(i).getEffective()+","+(healthDataObs.get(i).getTestValue()+healthDataObs.get(i).getTestUnit())
                    +","+healthDataObs.get(i).getInterpretation()+","+healthDataObs.get(i).getPerfomer()+",");
        }

        model.addAttribute("dataSet",dataSet.toString());

        return "heartRate";
    }

    @RequestMapping("/bloodPressure")
    String bloodPressure(@RequestParam(value="startDate", required = false, defaultValue = "value1")String startDate,
                         @RequestParam(value="endDate", required = false, defaultValue = "value2") String endDate, Model model){

        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);

        healthDataObs.clear();

        if(startDate.equals("value1")&&endDate.equals("value2")){
            ddl.getObservations().forEach(obs ->{
                if(obs.getCode().equals(LoincCode.BloodPressure)){
                    healthDataObs.add(new HealthData (obs));
                }
            });
            model.addAttribute("dateCheck","today");
        }else{
            //사용자가 설정한 date 기간의 값만 가져와서 출력
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date startValue = dateFormat.parse(startDate);
                Date endValue = dateFormat.parse(endDate);

                model.addAttribute("dateCheck","daterange");
                model.addAttribute("startDay",startDate);
                model.addAttribute("endDay",endDate);

                ddl.getObservations().forEach(obs ->{
                    if(obs.getCode().equals(LoincCode.BloodPressure)){
                        if(((obs.getEffective()).compareTo(startValue)>=0)&&((obs.getEffective()).compareTo(endValue)<=0)){
                            healthDataObs.add(new HealthData(obs));
                        }
                    }
                });
            }catch (ParseException e){
                e.printStackTrace();
            }
        }

        //SORT HEALTHDATAOBS
        sortData(healthDataObs);

        //CREATE CHARTDATA
        ArrayList<String> label = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();


        for(int i=0;i<healthDataObs.size();i++){
            label.add(healthDataObs.get(i).getEffective());
            value.add(healthDataObs.get(i).getTestValue().toString());
        }

        model.addAttribute("label",label);
        model.addAttribute("value",value);

        //DataTable
        StringBuffer dataSet = new StringBuffer();

        for(int i=0;i<healthDataObs.size();i++){
            dataSet.append(healthDataObs.get(i).getEffective()+","+(healthDataObs.get(i).getTestValue()+healthDataObs.get(i).getTestUnit())
                    +","+healthDataObs.get(i).getInterpretation()+","+healthDataObs.get(i).getPerfomer()+",");
        }

        model.addAttribute("dataSet",dataSet.toString());

        return "bloodPressure";
    }

    @RequestMapping("/bloodSugar")
    String bloodSugar(@RequestParam(value="startDate", required = false, defaultValue = "value1")String startDate,
                      @RequestParam(value="endDate", required = false, defaultValue = "value2") String endDate, Model model){
        ArrayList<HealthData> healthDataObs = new ArrayList<>();
        model.addAttribute("healthDataObs",healthDataObs);

        healthDataObs.clear();

        if(startDate.equals("value1")&&endDate.equals("value2")){
            ddl.getObservations().forEach(obs ->{
                if(obs.getCode().equals(LoincCode.Glucose)){
                    healthDataObs.add(new HealthData (obs));
                }
            });
            model.addAttribute("dateCheck","today");
        }else{
            //사용자가 설정한 date 기간의 값만 가져와서 출력
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date startValue = dateFormat.parse(startDate);
                Date endValue = dateFormat.parse(endDate);

                model.addAttribute("dateCheck","daterange");
                model.addAttribute("startDay",startDate);
                model.addAttribute("endDay",endDate);

                ddl.getObservations().forEach(obs ->{
                    if(obs.getCode().equals(LoincCode.Glucose)){
                        if(((obs.getEffective()).compareTo(startValue)>=0)&&((obs.getEffective()).compareTo(endValue)<=0)){
                            healthDataObs.add(new HealthData(obs));
                        }
                    }
                });
            }catch (ParseException e){
                e.printStackTrace();
            }
        }

        //SORT HEALTHDATAOBS
        sortData(healthDataObs);

        //CREATE CHARTDATA
        ArrayList<String> label = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();


        for(int i=0;i<healthDataObs.size();i++){
            label.add(healthDataObs.get(i).getEffective());
            value.add(healthDataObs.get(i).getTestValue().toString());
        }

        model.addAttribute("label",label);
        model.addAttribute("value",value);


        //DataTable
        StringBuffer dataSet = new StringBuffer();

        for(int i=0;i<healthDataObs.size();i++){
            dataSet.append(healthDataObs.get(i).getEffective()+","+(healthDataObs.get(i).getTestValue()+healthDataObs.get(i).getTestUnit())
                    +","+healthDataObs.get(i).getInterpretation()+","+healthDataObs.get(i).getPerfomer()+",");
        }

        model.addAttribute("dataSet",dataSet.toString());

        return "bloodSugar";
    }

    //SORTING HEALTHDATAOBS ACCENDING
    ArrayList<HealthData> sortData(ArrayList<HealthData> healthDataObs){
        Collections.sort(healthDataObs, new Comparator<HealthData>() {
            @Override
            public int compare(HealthData o1, HealthData o2) {
                return o1.getEffective().compareTo(o2.getEffective());
            }
        });
        return healthDataObs;
    }

    @Data
    public class HealthData{
            String effective;
            String testItem;
            Double testValue;
            String testUnit;
            String standards;
            String interpretation;
            String perfomer;

            public HealthData(){
                this.effective = "";
                this.testItem = "";
                this.testValue = 0.0;
                this.testUnit = "";
                this.standards = "";
                this.interpretation = "";
                this.perfomer = "";
            }

            public HealthData(GFObservation obs){
                this();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
                this.effective = dateFormat.format(obs.getEffective());
                this.testItem = obs.getCode().toString();
                this.testValue = obs.getValue();
                this.testUnit = obs.getUnit();
                this.standards = "";
                this.interpretation = obs.getInterpretation().getName();
                this.perfomer = obs.getPerformer();
            }
    }

}

