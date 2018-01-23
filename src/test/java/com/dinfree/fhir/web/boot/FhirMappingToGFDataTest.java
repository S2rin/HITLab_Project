package com.dinfree.fhir.web.boot;

import ca.uhn.fhir.model.dstu2.composite.*;
import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.model.dstu2.valueset.*;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.XhtmlDt;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import com.dinfree.fhir.web.domain.data.observation.GFObservation;
import com.dinfree.fhir.web.domain.data.observation.LoincCode;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by whitehobbit on 2016. 9. 7..
 */
public class FhirMappingToGFDataTest {

    FhirMappingToGFData fmg;
    int organizationId = 1;
    static final String practitionerId = "4";
    static final String patientId = "7";
    static final String observationId = "4";
    static final String hospital[] = {"길병원", "서울대병원", "성모병원"};

    @Before
    public void setUp() throws Exception {
        fmg = new FhirMappingToGFData("http://hitlab.gachon.ac.kr:8888/gachon-fhir-server/baseDstu2/");
    }

    @Test
    public void testSetData() throws Exception {
//        createOranization("길병원");
//        createOranization("서울대병원");
//        createOranization("성모병원");
//        createPractitioner("홍", "지홍", 1);
//        createPractitioner("유", "혜정", 2);
//        createPractitioner("정", "윤도", 3);
//        createPatient();
        createObservations(80);
    }

    @Test
    public void testMappingPatientToUser() throws Exception {
        Patient patient = fmg.fhirConnector.getClient().read().resource(Patient.class).withId(patientId).execute();
        GFUser user = fmg.mappingPatientToUser(patient);
    }

    @Test
    public void testGetObservationForPatientId() throws Exception {
        Bundle result = fmg.getObservationForPatientId(patientId);
    }

    @Test
    public void testMappingBundleToGFObservation() throws Exception {

        ArrayList<GFObservation> gfObservations= fmg.mappingBundleToGFObservation(fmg.getObservationForPatientId(patientId));
        for (GFObservation gfObservation : gfObservations) {
            System.out.println("id: " + gfObservation.getId() + " effective: " + gfObservation.getEffective() + " " + gfObservation.getCode().getCode() + " " + gfObservation.getValue() + gfObservation.getUnit());
        }
    }

    @Test
    public void testMappingObservationToGFObservation() throws Exception {

    }

    void createObservations(int num) {

        for (int i = 0; i < num; i++) {
            StringBuffer dateBuffer = new StringBuffer();
            dateBuffer.append("2016-").append(randNum(1, 12))
                    .append("-").append(randNum(1, 30))
                    .append("T").append(randNum(0, 24))
                    .append(":").append(randNum(0, 60))
                    .append(":").append(randNum(0, 60))
                    .append(".").append(randNum(0, 1000))
                    .append("+09:00");
            String date = dateBuffer.toString();

            createBloodPressure(randNum(80, 40), date);
        }

        for (int i = 0; i < 40; i++) {
            StringBuffer dateBuffer = new StringBuffer();
            dateBuffer.append("2016-").append(randNum(1, 12))
                    .append("-").append(randNum(1, 30))
                    .append("T").append(randNum(0, 24))
                    .append(":").append(randNum(0, 60))
                    .append(":").append(randNum(0, 60))
                    .append(".").append(randNum(0, 1000))
                    .append("+09:00");
            String date = dateBuffer.toString();

            createGlucose(randNum(80, 45), date);
        }

        for (int i = 0; i < 40; i++) {
            StringBuffer dateBuffer = new StringBuffer();
            dateBuffer.append("2016-").append(randNum(1, 12))
                    .append("-").append(randNum(1, 30))
                    .append("T").append(randNum(0, 24))
                    .append(":").append(randNum(0, 60))
                    .append(":").append(randNum(0, 60))
                    .append(".").append(randNum(0, 1000))
                    .append("+09:00");
            String date = dateBuffer.toString();

            createHeartRate(randNum(60, 40), date);
        }

        for (int i = 0; i < 40; i++) {
            StringBuffer dateBuffer = new StringBuffer();
            dateBuffer.append("2016-").append(randNum(1, 12))
                    .append("-").append(randNum(1, 30))
                    .append("T").append(randNum(0, 24))
                    .append(":").append(randNum(0, 60))
                    .append(":").append(randNum(0, 60))
                    .append(".").append(randNum(0, 1000))
                    .append("+09:00");
            String date = dateBuffer.toString();

            createWeight(randNum(55, 2), date);
        }
    }

    public void createBloodPressure(double value, String date) {
        int organizationId = randNum(1, 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date dateTime = null;
        try {
            dateTime = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Observation obs = new Observation();
        obs.setStatus(ObservationStatusEnum.FINAL);
        obs.getCode()
                .addCoding()
                .setSystem("http://loinc.org")
                .setCode(LoincCode.BloodPressure.getCode().toString())
                .setDisplay("Diastolic blood pressure");
        obs.setValue(new QuantityDt()
                .setValue(value)
                .setUnit("mmHg")
        );
        obs.setSubject(new ResourceReferenceDt()
                .setReference("Patient/" + patientId)
        );
        obs.setEffective(new DateTimeDt(dateTime));
        obs.setIssuedWithMillisPrecision(dateTime);
        obs.getBodySite().addCoding()
                .setSystem("http://snomed.info/sct")
                .setCode("368209003")
                .setDisplay("오른팔");
        obs.getPerformer()
                .add(new ResourceReferenceDt()
                        .setReference("Organization/" + organizationId)
                );
        fmg.fhirConnector.getClient().create().resource(obs).prettyPrint().encodedJson().execute();
    }

    public void createGlucose(double value, String date) {
        int organizationId = randNum(1, 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date dateTime = null;
        try {
            dateTime = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Observation obs = new Observation();
        obs.setStatus(ObservationStatusEnum.FINAL);
        obs.getCode()
                .addCoding()
                .setSystem("http://loinc.org")
                .setCode(LoincCode.Glucose.getCode().toString())
                .setDisplay("Glucose [Mass/volume] in Blood");
        obs.setValue(new QuantityDt()
                .setValue(value)
                .setUnit("mg/dL")
        );
        obs.setSubject(new ResourceReferenceDt("Patient/" + patientId));
        obs.setEffective(new DateTimeDt(dateTime));
        obs.setIssuedWithMillisPrecision(dateTime);
        obs.getPerformer()
                .add(new ResourceReferenceDt()
                        .setReference("Organization/" + organizationId)
                );
        fmg.fhirConnector.getClient().create().resource(obs).prettyPrint().encodedJson().execute();
    }

    public void createHeartRate(double value, String date) {
        int organizationId = randNum(1, 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date dateTime = null;
        try {
            dateTime = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Observation obs = new Observation();
        obs.setStatus(ObservationStatusEnum.FINAL);
        obs.getCode()
                .addCoding()
                .setSystem("http://loinc.org")
                .setCode(LoincCode.HeartRate.getCode().toString())
                .setDisplay("Heart Rate");
        obs.setValue(new QuantityDt()
                .setValue(value)
                .setUnit("bpm")
        );
        obs.setSubject(new ResourceReferenceDt("Patient/" + patientId));
        obs.setEffective(new DateTimeDt(dateTime));
        obs.setIssuedWithMillisPrecision(dateTime);
        obs.getPerformer()
                .add(new ResourceReferenceDt()
                        .setReference("Organization/" + organizationId)
                );
        fmg.fhirConnector.getClient().create().resource(obs).prettyPrint().encodedJson().execute();
    }

    public void createWeight(double value, String date) {
        int organizationId = randNum(1, 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date dateTime = null;
        try {
            dateTime = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Observation obs = new Observation();
        obs.setStatus(ObservationStatusEnum.FINAL);
        obs.getCode()
                .addCoding()
                .setSystem("http://loinc.org")
                .setCode(LoincCode.Weight.getCode().toString())
                .setDisplay("Weight Measured");
        obs.setValue(new QuantityDt()
                .setValue(value)
                .setUnit("kg")
        );
        obs.setSubject(new ResourceReferenceDt("Patient/" + patientId));
        obs.setEffective(new DateTimeDt(dateTime));
        obs.setIssuedWithMillisPrecision(dateTime);
        obs.getPerformer()
                .add(new ResourceReferenceDt()
                        .setReference("Organization/" + organizationId)
                );
        fmg.fhirConnector.getClient().create().resource(obs).prettyPrint().encodedJson().execute();
    }

    public void createPatient(String family, String given, String country, String city, String birthdate, ResourceReferenceDt organization, ResourceReferenceDt careProvider) {
        Patient patient = new Patient();
        patient.getText().setDiv("<div><div class='hapiHeaderText'><b>"+family+"</b>"+given+"</div><table class='hapiPropertyTable'><tbody><tr><td>주소</td><td><span>"+city+" </span><span>"+country+" </span></td></tr><tr><td>생년월일</td><td><span>"+birthdate+"</span></td></tr></tbody></table></div>");
        patient.getText().setStatus(NarrativeStatusEnum.GENERATED);
        patient.addIdentifier()
                .setSystem("http://acme.org/MRNs")
                .setValue("8051120");
        patient.addName()
                .addFamily(family)
                .addGiven(given);
        patient.addTelecom()
                .setSystem(ContactPointSystemEnum.PHONE)
                .setValue("+82 10-7769-1093")
                .setUse(ContactPointUseEnum.MOBILE);
        patient.setGender(AdministrativeGenderEnum.MALE);
        patient.setBirthDate(new DateDt(birthdate));
        patient.addAddress()
                .setCity(city)
                .setCountry(country);
        patient.addContact()
                .addRelationship()
                .addCoding()
                .setSystem("http://hl7.org/fhir/patient-contact-relationship")
                .setCode("partner");
        patient.addContact().getName().addFamily("이").addGiven("아랑");
        patient.addContact().addTelecom()
                .setSystem(ContactPointSystemEnum.PHONE)
                .setValue("+82 10-3333-4245")
                .setUse(ContactPointUseEnum.MOBILE);
        patient.addContact().setGender(AdministrativeGenderEnum.FEMALE);
        patient.getManagingOrganization().setReference(organization.getReference());
        patient.getCareProvider().add(careProvider);
    }

    public void createPatient() {
        organizationId = randNum(1, 3);
        Patient patient = new Patient();
        XhtmlDt theDiv = new XhtmlDt("<div><div class='hapiHeaderText'><b>이</b>진기</div><table class='hapiPropertyTable'><tbody><tr><td>주소</td><td><span>인천 </span><span>대한민국 </span></td></tr><tr><td>생년월일</td><td><span>1989.12.14</span></td></tr></tbody></table></div>");
        patient.setText(new NarrativeDt(theDiv, NarrativeStatusEnum.GENERATED));
        List<IdentifierDt> identifierDtList = new ArrayList<>();
        identifierDtList.add(new IdentifierDt("http://acme.org/MRNs", "8051120"));
        patient.setIdentifier(identifierDtList);
        List<HumanNameDt> humanNameDts = new ArrayList<HumanNameDt>();
        humanNameDts.add(new HumanNameDt().addFamily("이").addGiven("진기"));
        patient.setName(humanNameDts);
        List<ContactPointDt> contactPointDts = new ArrayList<>();
        contactPointDts.add(new ContactPointDt()
                .setSystem(ContactPointSystemEnum.PHONE)
                .setValue("+82 10-7769-1093")
                .setUse(ContactPointUseEnum.MOBILE));
        patient.setTelecom(contactPointDts);
        patient.setGender(AdministrativeGenderEnum.MALE);
        patient.setBirthDate(new DateDt(1989, 12, 14));
        List<AddressDt> addressDts = new ArrayList<>();
        addressDts.add(new AddressDt()
                .setCity("인천")
                .setCountry("대한민국"));
        patient.setAddress(addressDts);
        List<Patient.Contact> contacts = new ArrayList<>();
        contacts.add(new Patient.Contact()
                .addRelationship(new CodeableConceptDt("http://hl7.org/fhir/patient-contact-relationship", "partner"))
                .setName(new HumanNameDt()
                        .addFamily("이")
                        .addGiven("근호"))
                .addTelecom(new ContactPointDt()
                        .setSystem(ContactPointSystemEnum.PHONE)
                        .setValue("+82 10-3333-4245")
                        .setUse(ContactPointUseEnum.MOBILE))
                .setGender(AdministrativeGenderEnum.MALE));
        patient.setContact(contacts);
        patient.setManagingOrganization(new ResourceReferenceDt()
                .setReference("Organization/" + randNum(1, 3))
                .setDisplay(hospital[(organizationId-1)]));
        List<ResourceReferenceDt> careProviders = new ArrayList<>();
        careProviders.add(new ResourceReferenceDt()
                .setReference("Practitioner/" + practitionerId)
                .setDisplay("홍지홍"));
        patient.setCareProvider(careProviders);
        fmg.fhirConnector.getClient().create().resource(patient).prettyPrint().encodedJson().execute();
    }

    public void createOranization(String name) {
        Organization organization = new Organization();
        organization.setText(new NarrativeDt(new XhtmlDt("<div><p>" + name + "</p></div>"), NarrativeStatusEnum.GENERATED));
        organization.setName("길병원");
        fmg.fhirConnector.getClient().create().resource(organization).prettyPrint().encodedJson().execute();
    }

    public void createPractitioner(String family, String name, int oId) {
        Practitioner practitioner = new Practitioner();
        practitioner.setText(new NarrativeDt(new XhtmlDt("<div><p>Dr. " + family + name + "</p></div>"), NarrativeStatusEnum.GENERATED));
        practitioner.setActive(true);
        practitioner.setName(new HumanNameDt()
                .addFamily(family)
                .addGiven(name)
                .addPrefix("Dr"));
        practitioner.addPractitionerRole()
                .setManagingOrganization(new ResourceReferenceDt()
                        .setReference("Organization/" + oId)
                        .setDisplay(hospital[(oId-1)]))
                .setRole(new CodeableConceptDt("http://hl7.org/fhir/v2/0286", "RP"))
                .setPeriod(new PeriodDt()
                        .setStart(new DateTimeDt("2012-01-01"))
                        .setEnd(new DateTimeDt("2016-12-31")));
        fmg.fhirConnector.getClient().create().resource(practitioner).prettyPrint().encodedJson().execute();
    }

    int randNum(int start, int end) {
        return (int) (Math.random() * end) + start;
    }
}