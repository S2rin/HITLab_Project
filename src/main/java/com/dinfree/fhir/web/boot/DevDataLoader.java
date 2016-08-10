package com.dinfree.fhir.web.boot;

import com.dinfree.fhir.web.domain.data.GFData;
import com.dinfree.fhir.web.domain.data.observation.*;
import com.dinfree.fhir.web.domain.data.user.GFRelationship;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Spring-boot Sample Data Loader Example by dinfree
 * <HR>
 * 본 클래스는 프로젝트에 필요한 샘플데이터를 사전에 생성하고 프레임워크에 공급하기 위한 예제임
 *
 * @author Hee Joung Hwang(hwanghj@gachon.ac.kr)
 */
@Component
public class DevDataLoader {

    private GFUser user = new GFUser();
    private ArrayList<GFObservation> observations = new ArrayList<>();
    /**
     * 생성자에서 객체 리스트 생성
     */
    public DevDataLoader() {
        user.setId(1160);
        user.setName("이진기");
        user.setGender("남성");
        user.setAddress("대한민국 인천");
        user.setBirthdate("1989.12.14");
        user.setTelecom(new ArrayList<String>() { { add("010-7769-1093"); } });
        user.setRelationship(new GFRelationship("이근호", "010-3333-4245"));
        user.setCareProvider("김래원");
        user.setManagingOrganization("길병원");

        String[] hospital = {"길병원", "서울대병원", "성모병원"};

        for(int i = 0; i < 9; i++) {
            String date = "2016-08-0" + i + "T0" + i + ":0" + i + ":0" + i + ".00" + i + "+09:00";
            GFObservation heartRate = null;
            try {
                heartRate = new GFHeartRate(date, 64 + i * 2, "김래원", hospital[i%3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            observations.add(heartRate);
            GFObservation bloodPressure = null;
            try {
                bloodPressure = new GFBloodPressure(date, 110 - i * 3, "김래원", hospital[(i+1)%3], "오른쪽");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            observations.add(bloodPressure);
            GFObservation bloodSugar = null;
            try {
                bloodSugar = new GFBloodSugar(date, 70 + i * 3, "김래원", hospital[(i+2)%3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            observations.add(bloodSugar);
            GFObservation weight = null;
            try {
                weight = new GFWeight(date, 75.0 + i * 1.5, "김래원", "길병원");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            observations.add(weight);
        }


    }

    public GFUser getUser() { return user; }

    public ArrayList<GFObservation> getObservations() { return  observations; }

}