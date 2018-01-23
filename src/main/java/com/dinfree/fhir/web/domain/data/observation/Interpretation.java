package com.dinfree.fhir.web.domain.data.observation;

import lombok.Getter;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */
public enum Interpretation {
    NULL(""), HIGH("높음"), NORMAL("정상"), LOW("낮음");
    @Getter private String name;

    Interpretation(String name) {
        this.name = name;
    }
}