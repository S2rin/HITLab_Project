package com.dinfree.fhir.web.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Basic Controller Example
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
}
