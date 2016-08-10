package com.dinfree.fhir.web.domain.data.observation;

import lombok.Data;

import java.text.ParseException;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Data
public class GFBloodPressure extends GFObservation {
    public static int totalNum = 0;
    String bodySite;

    @Override
    public Interpretation calcInterpretation() {
        return null;
    }

    public GFBloodPressure() {
        super();
        this.totalNum++;
        this.code = LoincCode.BloodPressure;
        this.unit = "mmHg";
        this.bodySite = "";
    }

    public GFBloodPressure(String effective, double value, String performer, String managingOrganization, String bodySite) throws ParseException {
        super(effective, value, performer, managingOrganization);
        this.totalNum++;
        this.code = LoincCode.BloodPressure;
        this.unit = "mmHg";
        this.bodySite = bodySite;
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }
}
