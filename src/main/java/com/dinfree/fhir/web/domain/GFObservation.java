package com.dinfree.fhir.web.domain;

import java.util.Date;

/**
 * Created by whitehobbit on 2016. 8. 4..
 */

public class GFObservation extends GFData {
    String loincCode;
    String value;
    String unit;
    Date effectiveDate;
    GFUser performer;
}
