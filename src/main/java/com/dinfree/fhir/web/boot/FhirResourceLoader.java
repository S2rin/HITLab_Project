package com.dinfree.fhir.web.boot;

import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import com.dinfree.fhir.web.domain.data.observation.*;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by whitehobbit on 2016. 8. 11..
 */

@Component
public class FhirResourceLoader {

    @Getter private FhirMappingToGFData fhirMappingToGFData;
    @Getter private GFUser user;
    @Getter
    private ArrayList<GFObservation> observations;
    private String patientId = "7";

    public FhirResourceLoader() {
        fhirMappingToGFData = new FhirMappingToGFData();

        Patient patient = fhirMappingToGFData.fhirConnector.getClient().read().resource(Patient.class).withId(patientId).execute();
        user = fhirMappingToGFData.mappingPatientToUser(patient);
        observations = fhirMappingToGFData.mappingBundleToGFObservation(fhirMappingToGFData.getObservationForPatientId(patientId));

    }
}
