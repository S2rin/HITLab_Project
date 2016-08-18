package com.dinfree.fhir.web.test;


import com.dinfree.fhir.web.ctrl.ControllerExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring boot - MVC 단일 테스트
 * <HR>
 * <ul>
 *  <li>Spring boot 에서 Spring MVC 부분을 따로 테스트 하는 경우임</li>
 *
 * </ul>
 * Created by dinfree on 2016. 8. 13..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ControllerExample.class)
@ComponentScan(basePackages = {"com.dinfree.fhir.web"})
public class TestRestControllerExample {

    @Autowired
    private MockMvc mvc;

    /**
     * create test
     */
    @Test
    public void createTest() throws Exception {

        String resource = "{\n" +
                "    \"resourceType\":\"Patient\",\n" +
                "    \"meta\":{\n" +
                "        \"versionId\":\"1\",\n" +
                "        \"lastUpdated\":\"2015-12-17T06:45:23.977-05:00\"\n" +
                "    },\n" +
                "    \"text\":{\n" +
                "        \"status\":\"generated\",\n" +
                "        \"div\":\"<div><div class=\\\"hapiHeaderText\\\"> FREDRICA <b>SMITH </b></div><table class=\\\"hapiPropertyTable\\\"><tbody><tr><td>Identifier</td><td>3333333333</td></tr><tr><td>Address</td><td><span>29 WEST AVENUE </span><br /><span>MALTON </span><span>NORTH YORKSHIRE </span><span>GBR </span></td></tr><tr><td>Date of birth</td><td><span>13 December 1965</span></td></tr></tbody></table></div>\"\n" +
                "    },\n" +
                "    \"identifier\":[\n" +
                "        {\n" +
                "            \"use\":\"official\",\n" +
                "            \"system\":\"http://mio-Projekt.org/NHS\",\n" +
                "            \"value\":\"3333333333\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"name\":[\n" +
                "        {\n" +
                "            \"family\":[\n" +
                "                \"HeeJoung\"\n" +
                "            ],\n" +
                "            \"given\":[\n" +
                "                \"Gachon\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"gender\":\"female\",\n" +
                "    \"birthDate\":\"1965-12-13\",\n" +
                "    \"address\":[\n" +
                "        {\n" +
                "            \"line\":[\n" +
                "                \"29 WEST AVENUE\"\n" +
                "            ],\n" +
                "            \"city\":\"MALTON\",\n" +
                "            \"state\":\"NORTH YORKSHIRE\",\n" +
                "            \"postalCode\":\"YO32 5TT\",\n" +
                "            \"country\":\"GBR\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        mvc.perform(post("/restapi/create/")
                .content(resource))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
