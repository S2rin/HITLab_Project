package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.DataLoaderExample;
import com.dinfree.fhir.web.domain.TestObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Basic Controller Example by dinfree
 * <HR>
 * 본 클래스는 컨트롤러 예제로 파라미터 처리와 모델 객체 사용을 보여준다.
 * 사용법은 다음과 같다
 * <ul>
 *     <li>http://localhost:8080/hello</li>
 *     <li>http://localhost:8080/hello/name=hong</li>
 * </ul>
 * @see com.dinfree.fhir.web.FhirWebClientApplication
 * @author Hee Joung Hwang(hwanghj@gachon.ac.kr)
 */
@Controller
public class ControllerExample {

    // DevDataLoader 매핑
    @Autowired
    DataLoaderExample ddl;

    /**
     * Basic Request Mapping Example
     * @param name
     * @param model
     * @return
     *
     * 사용법은 다음과 같다
     * <ul>
     *     <li>http://localhost:8080/hello</li>
     *     <li>http://localhost:8080/hello/name=hong</li>
     * </ul>
     */
    @RequestMapping("/hello")
    String index(@RequestParam(value="name", required=false, defaultValue="HITLAB") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    /**
     * 단순 문자열 리턴 Rest API
     * @return
     */
    @RequestMapping("/hello/test")
    @ResponseBody
    String restresp() {
        return "Hello";
    }

    /**
     * 파라미터를 통해 처리된 결과를 json 으로 리턴
     * @param id
     * @return
     */
    @RequestMapping("/hello/test/{id}")
    @ResponseBody
    public ArrayList<TestObj> getAll(@PathVariable String id) {
        return ddl.getTestData();
    }


    /**
     * 모델 객체 생성 -> 컨트롤러와 연관이 있는 경우 사용함 @RequestMapping 에서 Model 객체 리턴해도 동일함
     * 단, 특정 요청 없이도 동작하게 하려면 이와 같이 해야 함.
     * @return
     */
    @ModelAttribute("testdata2")
    public ArrayList<TestObj> getData() {
        return ddl.getTestData();
    }
}
