package com.dinfree.fhir.web.domain.data.observation;

import lombok.Data;

import java.text.ParseException;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Data
public class GFBloodSugar extends GFObservation {
    public static int totalNum = 0;

    @Override
    public Interpretation calcInterpretation() {
        return null;
    }

    public GFBloodSugar() {
        super();
        this.totalNum++;
        this.code = LoincCode.Glucose;
        this.unit = "mg/dl";
    }

    public GFBloodSugar(String effective, double value, String performer, String managingOrganization) throws ParseException {
        super(effective, value, performer, managingOrganization);
        this.totalNum++;
        this.code = LoincCode.Glucose;
        this.unit = "mg/dl";
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }

}

