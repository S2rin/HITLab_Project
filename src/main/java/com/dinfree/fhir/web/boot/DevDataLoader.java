package com.dinfree.fhir.web.boot;

import com.dinfree.fhir.web.domain.data.observation.*;
import com.dinfree.fhir.web.domain.data.user.GFContact;
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
        user.setId("1160");
        user.setName("이진기");
        user.setGender("남성");
        user.setAddress("대한민국 인천");
        user.setBirthdate("1989.12.14");
        user.setTelecom(new ArrayList<String>() { { add("010-7769-1093"); } });
        user.setContact(new GFContact("이근호", "010-3333-4245"));
        user.setCareProvider("김래원");
        user.setManagingOrganization("길병원");

        this.createObsercation(40, new GFHeartRate());
        this.createObsercation(40, new GFBloodPressure());
        this.createObsercation(40, new GFGlucose());
        this.createObsercation(40, new GFWeight());

    }

    public GFUser getUser() { return user; }

    public ArrayList<GFObservation> getObservations() { return  observations; }

    int randNum(int start, int range) {
        return (int) (Math.random() * range) + start;
    }

    boolean createObsercation(int num, GFObservation observation) {
        boolean flag = false;
        String[] hospital = {"길병원", "서울대병원", "성모병원"};
        for(int i = 0; i < num; i++) {
            StringBuffer dateBuffer = new StringBuffer();
            dateBuffer.append("2016-").append(randNum(1, 12))
                    .append("-").append(randNum(1, 30))
                    .append("T").append(randNum(0, 24))
                    .append(":").append(randNum(0, 60))
                    .append(":").append(randNum(0, 60))
                    .append(".").append(randNum(0, 1000))
                    .append("+09:00");

            String date = dateBuffer.toString();

            if (observation.getClass().equals(GFHeartRate.class)) {
                try {
                    observation = new GFHeartRate(date, randNum(60, 40), hospital[randNum(0, 2)]);
                    flag = true;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (observation.getClass().equals(GFBloodPressure.class)) {
                try {
                    observation = new GFBloodPressure(date, randNum(80, 40), hospital[randNum(0, 2)], "오른쪽");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (observation.getClass().equals(GFGlucose.class)) {
                try {
                    observation = new GFGlucose(date, randNum(80, 45), hospital[randNum(0, 2)]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (observation.getClass().equals(GFWeight.class)) {
                try {
                    observation = new GFWeight(date, randNum(50, 15), hospital[randNum(0, 2)]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            observations.add(observation);
        }
        return flag;
    }
}