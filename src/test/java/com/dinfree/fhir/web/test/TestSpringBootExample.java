package com.dinfree.fhir.web.test;

import com.dinfree.fhir.web.boot.DataLoaderExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by dinfree on 2016. 8. 13..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSpringBootExample {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DataLoaderExample tdl;

    /**
     * 스프링 객체에 대한 테스트, 여기서는 TestDataLoader 정상 생성 및 데이터 확인
     */
    @Test
    public void testDataLoadTest() {
        assert(this.tdl.getTestData().get(0).getName().equals("hong"));
    }

    /**
     * Restful api 요청시 응답에 대한 테스트
     * 응답 값이 단순 문자열인 경우, 컨트롤러에서 기본으로 Hello 를 리턴함.
     */
    @Test
    public void simpleRestTest() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/hello/test", String.class);
        assertEquals(entity.getBody(),"Hello");
    }
}
