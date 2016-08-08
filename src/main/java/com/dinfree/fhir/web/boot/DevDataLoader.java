package com.dinfree.fhir.web.boot;

import com.dinfree.fhir.web.domain.*;
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

    private ArrayList<GFData> testdata = new ArrayList<>();

    /**
     * 생성자에서 객체 리스트 생성
     */
    public DevDataLoader() {
        GFUser user = new GFUser();
        user.setId(1160);
        user.setName("이진기");
        user.setGender("남성");
        user.setAddress("대한민국 인천");
        user.setBirthdate("1989.12.14");
        user.setPhone(new ArrayList<String>() { { add("010-7769-1093"); } });
        user.setContactPerson(new GFContactPerson("이근호", "010-3333-4245"));
        user.setCareProvider("김래원");
        user.setCareProviderOrganization("길병원");

        GFObservation obs1 = new GFObservation();

        testdata.add(user);


    }

    public ArrayList getTestData() {
        return testdata;
    }
}