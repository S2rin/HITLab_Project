package com.dinfree.fhir.web.boot;

import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by whitehobbit on 2016. 8. 8..
 */
public class DevDataLoaderTest {

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testGetObservations() throws Exception {
        DevDataLoader ddl = new DevDataLoader();
        for (GFObservation obs : ddl.getObservations()) {
            System.out.println(obs);
        }
    }
}