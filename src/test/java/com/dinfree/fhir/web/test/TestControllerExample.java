package com.dinfree.fhir.web.test;


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
public class TestControllerExample {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DataLoaderExample tdl;

    /**
     * TestDataLoader 를 가상으로 만들어 컨텍스트에 공급한다.
     * 테스트 과정에서 TestDataLoader 는 주어진 데이터를 생성하는 것으로 대치됨.
     */
    @Before
    public void setup() {
        ArrayList<TestObj> testObj = new ArrayList<TestObj>();
        testObj.add(new TestObj("hwang",10));
        given(this.tdl.getTestData()).willReturn(testObj);
    }

    /**
     * 실제 데이터와의 비교를 통해 컨트롤러 동작 확인
     * 이경우에는 MockBean 을 사용하면 안됨.
     * @throws Exception

    @Test
    public void realDataTest() throws Exception{
        mvc.perform(get("/hello/test/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",iterableWithSize(3)))
                .andExpect(jsonPath("$[0]['name']",containsString("hong")));
    }
    */

    /**
     * Mock Object 를 이용한 테스트
     */
    @Test
    public void mockDataTest() throws Exception {
        mvc.perform(get("/hello/test/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",iterableWithSize(1)))
                .andExpect(jsonPath("$[0]['name']",containsString("hwang")));
    }

    /**
     * 뷰 연동 테스트
     */
    @Test
    public void helloViewTest() throws Exception {
        MvcResult result = this.mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(view().name("test"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertNotNull(content);

        // 모델에 저장된 속성값을 가지고와 정상적으로 처리 되었는지 비교함. 컨트롤러에서 기본값은 HITLAB로 되어 있음
        // model 접근을 좀 더 쉽게 할 수 있는 방법이 있는지는 좀 더 찾아봐야 함.
        assertEquals(result.getModelAndView().getModel().get("name"),"TEST");
    }
}
