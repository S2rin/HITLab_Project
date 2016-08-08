package com.dinfree.fhir.web.domain.data.observation;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */
public class GFWeight extends GFObservation {
    public static int totalNum = 0;

    @Override
    public Interpretation calcInterpretation() {
        return null;
    }

    public GFWeight() {
        super();
        this.totalNum++;
        this.code = LoincCode.WEIGHT;
    }

    public GFWeight(String effective, double value, String performer) {
        super(effective, value, performer);
        this.totalNum++;
        this.code = LoincCode.WEIGHT;
    }

    public int getObsTotalNum() { return super.getTotalNum(); }

    public static int getTotalNum() {
        return totalNum;
    }

}
