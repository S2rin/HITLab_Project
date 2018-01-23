package com.dinfree.fhir.web.domain.data.observation;

import lombok.Data;

import java.text.ParseException;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Data
public class GFGlucose extends GFObservation {
    public static int totalNum = 0;

    @Override
    public Interpretation calcInterpretation() {
        return null;
    }

    public GFGlucose() {
        super();
        this.totalNum++;
        this.code = LoincCode.Glucose;
        this.unit = "mg/dl";
    }

    public GFGlucose(String effective, double value, String performer) throws ParseException {
        super(effective, value, performer);
        this.totalNum++;
        this.code = LoincCode.Glucose;
        this.unit = "mg/dl";
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }

}