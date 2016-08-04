package com.dinfree.fhir.web.config;

/**
 * Spring-boot Web Configuration 클래스 예제
 * <HR>
 * 본 클래스는 프로젝트에서 필요한 객체들을 초기화 하고 빈 객체를 생성하는 예제 이다.
 */
import com.dinfree.fhir.web.boot.DevDataLoader;
import com.dinfree.fhir.web.domain.TestObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    DevDataLoader ddl;

    @Bean(name="testdata")
    public ArrayList<TestObj> getDDL() {
        return ddl.getTestData();
    }

    @Bean(name="test")
    public String getTest() {
        return "Test Message";
    }
}