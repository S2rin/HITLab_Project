package com.dinfree.fhir.web.boot;

import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.rest.api.SummaryEnum;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import com.dinfree.fhir.web.domain.data.observation.*;
import com.dinfree.fhir.web.domain.data.user.GFContact;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by whitehobbit on 2016. 9. 7..
 */

public class FhirMappingToGFData {

    FhirConnector fhirConnector;
    String fhirServerBaseUrl = "http://hitlab.gachon.ac.kr:8888/gachon-fhir-server/baseDstu2/";

    public FhirMappingToGFData() {
        FhirServerConnectionMaker fhirServerConnectionMaker = new FhirServerConnectionMaker();
        fhirConnector = fhirServerConnectionMaker.makeConnection(fhirServerBaseUrl);
    }

    public FhirMappingToGFData(String fhirServerBaseUrl) {
        FhirServerConnectionMaker fhirServerConnectionMaker = new FhirServerConnectionMaker();
        fhirConnector = fhirServerConnectionMaker.makeConnection(fhirServerBaseUrl);
    }

    public GFUser mappingPatientToUser(Patient patient) {
        String name;
        String birthdate;
        String gender;
        String address;
        List<String> telecom;
        GFContact contact = new GFContact();
        String careProvider;
        String managingOrganization;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        name = patient.getNameFirstRep().getFamilyAsSingleString() + patient.getNameFirstRep().getGivenAsSingleString();
        birthdate = dateFormat.format(patient.getBirthDate());
        gender = patient.getGender();
        List<AddressDt> addressDt = patient.getAddress();
        address = getAddressAsString(addressDt);
        telecom = new ArrayList<String>();
        patient.getTelecom().forEach(tel -> {
            telecom.add(tel.getValue());
        });
        contact.setName(patient.getContactFirstRep().getName().getFamilyAsSingleString() + " " + patient.getContactFirstRep().getName().getGivenAsSingleString());
        contact.setTelecom(patient.getContactFirstRep().getTelecomFirstRep().getValue());

        //careProvider
        if (patient.getCareProvider().isEmpty()) {
            careProvider = "";
        }  else {
            String resourceName = patient.getCareProvider().get(0).getReference().getResourceType();
            String id = patient.getCareProvider().get(0).getReference().getIdPart();
            if (resourceName.equals("Practitioner")) {
                Practitioner practitioner = new Practitioner();
                practitioner = fhirConnector.getClient().read(practitioner.getClass(), id);
//                practitioner = readResource(practitioner, id);
                careProvider = practitioner.getName().getFamilyAsSingleString() + " " + practitioner.getName().getGivenAsSingleString();
            } else {
                Organization organization = new Organization();
//                organization = fhirConnector.getClient().read().resource(organization.getClass()).withId(id).execute();
//                organization = readResource(organization, id);
                careProvider = organization.getName();
            }
        }

        // managingOrganization
        if (patient.getManagingOrganization().isEmpty()) {
            managingOrganization = "";
        } else {
            String resourceName = patient.getManagingOrganization().getReference().getResourceType();
            String id = patient.getManagingOrganization().getReference().getIdPart();
            Organization organization = new Organization();
            organization = fhirConnector.getClient().read(organization.getClass(), id);
//            organization = readResource(organization, id);
            managingOrganization = organization.getName().toString();
        }

        GFUser user = new GFUser(name, birthdate, gender, address, telecom, contact, careProvider, managingOrganization);
        return user;
    }

    Bundle getObservationForPatientId(String patientId) {
        Bundle tmp = fhirConnector.getClient()
                .search()
                .forResource(Observation.class)
                .where(new StringClientParam("patient").matches().value(patientId))
                .returnBundle(Bundle.class)
                .execute();
        Bundle bundle = getObservations(tmp);
        return bundle;
    }

    Bundle getObservations(Bundle start) {
        Bundle bundle = start;
        if (start.getLink(Bundle.LINK_NEXT) != null) {
            Bundle next = getObservations(fhirConnector.getClient().loadPage().next(start).execute());
            for (Bundle.Entry entry : next.getEntry()) {
                start.addEntry(entry);
            }
        }
        return bundle;
    }

    ArrayList<GFObservation> mappingBundleToGFObservation(Bundle bundle) {
        ArrayList<GFObservation> gfObservations =  new ArrayList<>();
        gfObservations.clear();
        bundle.getEntry().forEach(resource -> {
            try {
                gfObservations.add(mappingObservationToGFObservation((Observation) resource.getResource()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
//        bundle.getResources(Observation.class).forEach(obs -> {
//            try {
//                gfObservations.add(mappingObservationToGFObservation(obs));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        });
        return gfObservations;
    }

    GFObservation mappingObservationToGFObservation(Observation observation) throws ParseException {
        GFObservation gfobs = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date effectiveDate = ((DateTimeDt)observation.getEffective()).getValue();
        String effective = dateFormat.format(effectiveDate);
        double value = 0.0;

        try {
            value = Double.parseDouble(((QuantityDt) observation.getValue()).getValue().toString());
        } catch (Exception e) {
            value = 0.0;
        }

//        System.out.println(value);
//        System.out.println(observation.getCode().getCodingFirstRep().getCode());
//        System.out.println(observation.getPerformer().get(0).getReference().getIdPart());

        Organization organization = fhirConnector.getClient().read(Organization.class, (observation.getPerformer().get(0).getReference().getIdPart()));
        String performer = organization.getName();
//        System.out.println(performer);
//        System.out.println(observation.getCode().getCodingFirstRep().getCode().toString());
//        System.out.println(LoincCode.BloodPressure.getCode().toString());

        if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.BloodPressure.getCode().toString())) {
            gfobs = new GFBloodPressure(effective, value, performer);
        } else if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.Glucose.getCode())) {
            gfobs = new GFGlucose(effective, value, performer);
        } else if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.HeartRate.getCode())) {
            gfobs = new GFHeartRate(effective, value, performer);
        } else if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.Weight.getCode())) {
            gfobs = new GFWeight(effective, value, performer);
        } else {
            gfobs = null;
        }
        gfobs.setId(observation.getId().getIdPart());
        return gfobs;
    }

//    GFObservation mappingObservationToGFObservation(Observation observation) throws ParseException {
//        GFObservation gfobs = null;
//        String effective = ((DateTimeDt)observation.getEffective()).getValueAsString();
//        double value;
//        try {
//            value = Double.parseDouble(((QuantityDt)observation.getValue()).getValue().toString());
//        } catch (Exception e) {
//            value = 0.0;
//        }
//        System.out.println(value);
//        Organization organization = new Organization();
//        organization = fhirConnector.getClient().read(organization.getClass(), observation.getPerformer().get(0).getReference().getIdPart());
//        String performer = organization.getName();
//
//        if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.BloodPressure.getCode())) {
//            gfobs = new GFBloodPressure(effective, value, performer);
//        } else if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.Glucose.getCode())) {
//            gfobs = new GFGlucose(effective, value, performer);
//        } else if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.HeartRate.getCode())) {
//            gfobs = new GFHeartRate(effective, value, performer);
//        } else if (observation.getCode().getCodingFirstRep().getCode().toString().equals(LoincCode.Weight.getCode())) {
//            gfobs = new GFWeight(effective, value, performer);
//        } else {
//            gfobs = null;
//        }
//
//        return gfobs;
//    }

    String checkNullToString(String str) {
        return str != null ? str : "";
    }

    String getAddressAsString(List<AddressDt> addressDts) {
        String str = "";
        for (AddressDt addressDt : addressDts) {
            String tmp = "";
            tmp = checkNullToString(addressDt.getUse());
            if (!tmp.isEmpty()) {
                str += "\n [" + tmp + "]\n    ";
            }
            tmp = checkNullToString(addressDt.getCountry());
            if (!tmp.isEmpty()) {
                str += tmp;
            }
            tmp = checkNullToString(addressDt.getState());
            if (!tmp.isEmpty()) {
                str += tmp;
            }
            tmp = checkNullToString(addressDt.getCity());
            if (!tmp.isEmpty()) {
                str += tmp;
            }
        }
        return str;
    }
}
