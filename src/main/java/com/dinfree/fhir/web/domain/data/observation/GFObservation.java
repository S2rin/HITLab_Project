package com.dinfree.fhir.web.domain.data.observation;

import com.dinfree.fhir.web.domain.data.GFData;

/**
 * Created by whitehobbit on 2016. 8. 4..
 */

public abstract class GFObservation extends GFData {
    static int totalNum = 0;
    int patientId;
    LoincCode code;
    String effective;
    double value;
    String performer;
    Interpretation interpretation;
    public abstract Interpretation calcInterpretation();

    public GFObservation() {
        super();
        this.patientId = 0;
        this.totalNum++;
        this.code = LoincCode.BLOODPRESSURE;
        this.effective = "";
        this.value = 0;
        this.performer = "";
        this.interpretation = Interpretation.NORMAL;
    }

    public GFObservation(String effective, double value, String performer) {
        this();
        this.effective = effective;
        this.value = value;
        this.performer = performer;
        this.interpretation = interpretation;
    }

    public static int getTotalNum() {
        return totalNum;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LoincCode getCode() {
        return code;
    }

    public void setCode(LoincCode code) {
        this.code = code;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public Interpretation getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return "GFObservation{" +
                "code=" + code +
                ", effective='" + effective + '\'' +
                ", value='" + value + '\'' +
                ", performer='" + performer + '\'' +
                ", interpretation=" + interpretation +
                '}';
    }
}