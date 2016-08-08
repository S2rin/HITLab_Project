package com.dinfree.fhir.web.component;

import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Component
public class HospitalResult {
    private ArrayList<GFObservation> hospitalResult = new ArrayList<>();

    public HospitalResult() {
        hospitalResult.clear();
    }

    public ArrayList<GFObservation> getHospitalResult() {
        return hospitalResult;
    }
}
