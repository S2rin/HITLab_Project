package com.dinfree.fhir.web.domain.data.observation;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */
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
        this.code = LoincCode.BLOODPRESSURE;
        this.bodySite = "";
    }

    public GFBloodPressure(String effective, double value, String performer, String managingOrganization, String bodySite) {
        super(effective, value, performer, managingOrganization);
        this.totalNum++;
        this.code = LoincCode.BLOODPRESSURE;
        this.bodySite = bodySite;
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }

    public String getBodySite() {
        return bodySite;
    }

    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }
}
