package com.dinfree.fhir.web.ctrl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import org.hl7.fhir.instance.model.api.IIdType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dinfree on 2016. 8. 18..
 */
@RestController
public class RestControllerExample {
    // Base URL
    // String base = "http://hitlab.gachon.ac.kr:8080/hapi-fhir-jpaserver-example/baseDstu2";
    String base = "http://fhirtest.uhn.ca/baseDstu2";

    // DSTU 버전 컨텍스트 객체 생성
    FhirContext ctx = FhirContext.forDstu2();

    // Base URL 로 부터 클라이언트 객체 생성
    IGenericClient client = ctx.newRestfulGenericClient(base);


    @PostMapping(value = "/restapi/create")
    public String create(@RequestBody String resource) {
        System.out.println(resource);
        // Patient 리소스 생성
        Patient newPatient;

        newPatient = (Patient) ctx.newJsonParser().parseResource(resource);

        // Create the resource on the server
        MethodOutcome outcome = client
                .create()
                .resource(newPatient)
                .execute();

        // Log the ID that the server assigned
        IIdType id = outcome.getId();
        return("Created patient, got ID: " + id);
    }

}
