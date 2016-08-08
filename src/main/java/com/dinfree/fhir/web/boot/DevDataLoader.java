package com.dinfree.fhir.web.boot;

import com.dinfree.fhir.web.domain.data.GFData;
import com.dinfree.fhir.web.domain.data.observation.*;
import com.dinfree.fhir.web.domain.data.user.GFRelationship;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.springframework.stereotype.Component;

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

        for(int i = 0; i < 9; i++) {
            GFObservation heartRate = new GFHeartRate(("2016-08-0" + 1 + i +"T12:03:00.003+09:00"), 64 + i * 2, "김래원");
            observations.add(heartRate);
            GFObservation bloodPressure = new GFBloodPressure(("2016-08-0" + 1 + i +"T12:03:00.003+09:00"), 110 - i * 3, "김래원", "오른쪽");
            observations.add(bloodPressure);
            GFObservation bloodSugar = new GFBloodSugar(("2016-08-0" + 1 + i +"T12:03:00.003+09:00"), 70 + i * 3, "김래원");
            observations.add(bloodSugar);
            GFObservation weight = new GFWeight(("2016-08-0" + 1 + i +"T12:03:00.003+09:00"), 75.0 + i * 1.5, "김래원");
        }


    }

    public GFUser getUser() { return user; }

    public ArrayList<GFObservation> getObservations() { return  observations; }

}