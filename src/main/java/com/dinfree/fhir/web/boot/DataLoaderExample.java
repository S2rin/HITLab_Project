package com.dinfree.fhir.web.boot;

import com.dinfree.fhir.web.domain.TestObj;
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
public class DataLoaderExample {

    private ArrayList<TestObj> testdata = new ArrayList<>();

    /**
     * 생성자에서 객체 리스트 생성
     */
    public DataLoaderExample() {
        testdata.add(new TestObj("hong",1));
        testdata.add(new TestObj("kang",2));
        testdata.add(new TestObj("kim",3));
    }

    public ArrayList<TestObj> getTestData() {
        return testdata;
    }
}