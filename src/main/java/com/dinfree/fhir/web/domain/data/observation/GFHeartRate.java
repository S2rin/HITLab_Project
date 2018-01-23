package com.dinfree.fhir.web.domain.data.observation;

import lombok.Data;

import java.text.ParseException;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Data
public class GFHeartRate extends GFObservation {
    public static int totalNum = 0;

    @Override
    public Interpretation calcInterpretation() {
        return null;
    }

    public GFHeartRate() {
        super();
        this.totalNum++;
        this.code = LoincCode.HeartRate;
        this.unit = "bpm";
    }

    public GFHeartRate(String effective, double value, String performer) throws ParseException {
        super(effective, value, performer);
        this.totalNum++;
        this.code = LoincCode.HeartRate;
        this.unit = "bpm";
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }

}
