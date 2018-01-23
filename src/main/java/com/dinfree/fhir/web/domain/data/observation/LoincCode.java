package com.dinfree.fhir.web.domain.data.observation;

import lombok.Getter;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

public enum LoincCode {
    Null("", ""), Glucose("혈당", "2339-0"), BloodPressure("혈압", "8462-4"), HeartRate("심박", "8867-4"), Weight("체중", "3141-9");

    @Getter private String name;
    @Getter private String code;

    LoincCode(String name, String code) {
        this.name = name;
        this.code = code;
    }
}