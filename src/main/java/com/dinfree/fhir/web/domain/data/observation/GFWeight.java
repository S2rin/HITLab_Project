package com.dinfree.fhir.web.domain.data.observation;

import lombok.Data;

import java.text.ParseException;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */

@Data
public class GFWeight extends GFObservation {
    public static int totalNum = 0;

    @Override
    public Interpretation calcInterpretation() {
        return null;
    }

    public GFWeight() {
        super();
        this.totalNum++;
        this.code = LoincCode.Weight;
        this.unit = "Kg";
    }

    public GFWeight(String effective, double value, String performer, String managingOrganization) throws ParseException {
        super(effective, value, performer, managingOrganization);
        this.totalNum++;
        this.code = LoincCode.Weight;
        this.unit = "Kg";
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }
}
