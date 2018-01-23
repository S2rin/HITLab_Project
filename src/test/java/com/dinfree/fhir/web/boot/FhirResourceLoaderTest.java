package com.dinfree.fhir.web.boot;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.*;
import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.model.dstu2.valueset.*;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.XhtmlDt;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import com.dinfree.fhir.web.domain.data.observation.LoincCode;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.hibernate.boot.model.naming.Identifier;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by whitehobbit on 2016. 8. 12..
 */
public class FhirResourceLoaderTest {
    FhirResourceLoader frl;

    @Before
    public void setUp() throws Exception {
        frl = new FhirResourceLoader();
    }

    @Test
    public void testName() throws Exception {
        System.out.println(frl.getFhirMappingToGFData().fhirConnector.getServerBaseUrl());
    }
}