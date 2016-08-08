package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.DevDataLoader;
import com.dinfree.fhir.web.domain.TestObj;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public class TestController {

    // DevDataLoader 매핑
    @Autowired DevDataLoader ddl;

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
     * 모델 객체 생성 -> 컨트롤러와 연관이 있는 경우 사용함 @RequestMapping 에서 Model 객체 리턴해도 동일함
     * 단, 특정 요청 없이도 동작하게 하려면 이와 같이 해야 함.
     * @return
     */
    @ModelAttribute("user")
    public GFUser getUser() {
        return ddl.getUser();
    }
}
