package com.dinfree.fhir.web.ctrl;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hitlab on 2016. 9. 12..
 */
public class HealthDataControllerTest {
//    HealthDataController hdc;
//    ArrayList<HealthDataController.HealthData> obs;
    @Before
    public void setUp() throws Exception {
//        hdc = new HealthDataController();
//        FhirResourceLoader frl = new FhirResourceLoader();
//        for (GFObservation gfObservation : frl.getObservations()) {
////            obs.add(new HealthDataController.HealthData(gfObservation));
//        }

    }

    @Test
    public void testChartData() throws Exception {
        String chart = "{ 'x': '2016-08-01 01:30', 'y': '75' }";
        JSONObject json = new JSONObject(chart);

        System.out.println(chart);
        System.out.println(json.toString());
    }
}