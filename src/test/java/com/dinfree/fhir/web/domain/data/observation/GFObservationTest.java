package com.dinfree.fhir.web.domain.data.observation;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by whitehobbit on 2016. 8. 10..
 */
public class GFObservationTest {
    @Test
    public void testConstruction() throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GFObservation heartRate = new GFHeartRate(("2016-10-08T21:39:56.251+09:00"), 64, "김래원", "길병원");
        System.out.println(dateFormat.format(heartRate.getEffective()));
        System.out.println(heartRate.getEffective());
        System.out.println(heartRate.dateFormat.format(heartRate.getEffective()));
        System.out.println(dateFormat.format(date));
    }
}