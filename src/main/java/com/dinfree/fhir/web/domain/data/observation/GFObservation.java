package com.dinfree.fhir.web.domain.data.observation;

import com.dinfree.fhir.web.domain.data.GFData;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by whitehobbit on 2016. 8. 4..
 */

@Data
public abstract class GFObservation extends GFData {
    static int totalNum = 0;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    int patientId;
    LoincCode code;
    Date effective;
    double value;
    String unit;
    String performer;
    Interpretation interpretation;
    String managingOrganization;

    public abstract Interpretation calcInterpretation();

    public GFObservation() {
        super();
        this.patientId = 0;
        this.totalNum++;
        this.code = LoincCode.Null;
        this.unit = "";
        this.effective = new Date();
        this.value = 0;
        this.performer = "";
        this.interpretation = Interpretation.NORMAL;
        this.managingOrganization = "";
    }

    public GFObservation(String effective, double value, String performer) {
        this();
        try {
            this.effective = dateFormat.parse(effective);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.value = value;
        this.performer = performer;
        // this.interpretation = calcInterpretation();
    }

    public GFObservation(String effective, double value, String performer, String managingOrganization) {
        this();
        try {
            this.effective = dateFormat.parse(effective);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.value = value;
        this.performer = performer;
        // this.interpretation = calcInterpretation();
        this.managingOrganization = managingOrganization;
    }

    public static int getTotalNum() {
        return totalNum;
    }
}