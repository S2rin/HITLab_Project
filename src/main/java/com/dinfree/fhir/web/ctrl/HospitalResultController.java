package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.DevDataLoader;
import com.dinfree.fhir.web.component.HospitalResult;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Controller
public class HospitalResultController {

    @Autowired DevDataLoader ddl;
    @Autowired HospitalResult hospitalResult;

    @RequestMapping("/")
    String index(Model model) {

        for (GFObservation obs : ddl.getObservations()) {
            if(obs.getManagingOrganization().equals("길병원")) {
                hospitalResult.getHospitalResult().add(obs);
            }
        }

        return "hospitalResult";
    }

    @ModelAttribute("hospitalResult")
    public ArrayList<GFObservation> getHospitalResult() {
        return hospitalResult.getHospitalResult();
    }
}
