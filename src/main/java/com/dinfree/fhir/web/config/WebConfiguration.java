package com.dinfree.fhir.web.config;

/**
 * Spring-boot Web Configuration 클래스 예제
 * <HR>
 * 본 클래스는 프로젝트에서 필요한 객체들을 초기화 하고 빈 객체를 생성하는 예제 이다.
 */
import com.dinfree.fhir.web.boot.DevDataLoader;
import com.dinfree.fhir.web.domain.GFData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.nio.charset.Charset;
import java.util.ArrayList;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    DevDataLoader ddl;

    @Bean(name="testdata")
    public ArrayList<GFData> getDDL() {
        return ddl.getTestData();
    }

    @Bean(name="test")
    public String getTest() {
        return "Test Message";
    }

    // 결과 출력시 강제로 UTF-8 설정
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    // POST 요청시 한글 깨짐 방지
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}