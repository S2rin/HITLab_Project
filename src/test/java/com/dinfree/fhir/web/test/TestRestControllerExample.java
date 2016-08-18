package com.dinfree.fhir.web.test;


import com.dinfree.fhir.web.boot.DataLoaderExample;
import com.dinfree.fhir.web.ctrl.ControllerExample;
import com.dinfree.fhir.web.domain.TestObj;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        String resource = "";
        mvc.perform(post("/restapi/create/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
