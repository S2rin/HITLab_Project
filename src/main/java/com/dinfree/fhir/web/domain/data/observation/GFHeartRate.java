package com.dinfree.fhir.web.domain.data.observation;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */
public class GFHeartRate extends GFObservation {
    public static int totalNum = 0;

    @Override
    public Interpretation calcInterpretation() {
        return null;
    }

    public GFHeartRate() {
        super();
        this.totalNum++;
        this.code = LoincCode.HEARTRATE;
    }

    public GFHeartRate(String effective, double value, String performer, String managingOrganization) {
        super(effective, value, performer, managingOrganization);
        this.totalNum++;
        this.code = LoincCode.HEARTRATE;
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }

}
