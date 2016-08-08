package com.dinfree.fhir.web.domain;

import java.util.Date;
import java.util.Enumeration;

/**
 * Created by whitehobbit on 2016. 8. 4..
 */

public class GFObservation {
    static int totalNum = 0;
    String code;
    String effective;
    String value;
    String performer;
}

enum Interpretation {
    HIGH, NORMAL, LOW;
}