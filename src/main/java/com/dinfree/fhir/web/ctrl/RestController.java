package com.dinfree.fhir.web.ctrl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.base.resource.BaseOperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dinfree on 2016. 8. 18..
 */
@org.springframework.web.bind.annotation.RestController
public class RestController{
    // Base URL
    // String base = "http://hitlab.gachon.ac.kr:8080/hapi-fhir-jpaserver-example/baseDstu2";
    String base = "http://fhirtest.uhn.ca/baseDstu2";

    // DSTU 버전 컨텍스트 객체 생성
    FhirContext ctx = FhirContext.forDstu2();

    // Base URL 로 부터 클라이언트 객체 생성
    IGenericClient client = ctx.newRestfulGenericClient(base);


    // create
    @PostMapping(value = "/restapi/create")
    @ResponseBody
    public String create(@RequestBody(required = false) String resource) {
        // Patient 리소스 생성
        BaseResource createdResource;
        MethodOutcome outcome = null;

        try {
            createdResource = (BaseResource) ctx.newJsonParser().parseResource(resource);
            // Create the resource on the server
            outcome = client
                    .create()
                    .resource(createdResource)
                    .execute();
        } catch (Exception e) {
            return e.toString();
        }

        // Log the ID that the server assigned
        return ("Created Resource: " + outcome.getId());
    }


    // update
    @RequestMapping(value = "/restapi/update")
    public String update(@RequestBody(required = false) String resource) {
        // Patient 리소스 생성
        BaseResource updatedResource;
        try {
            updatedResource = (BaseResource) ctx.newJsonParser().parseResource(resource);
        } catch (Exception e) {
            return e.toString();
        }

        // Update the resource on the server
        MethodOutcome outcome = client.update().resource(updatedResource).execute();
        return ("Updated Resource: " + outcome.getId());
    }


    // read
    @GetMapping(value = "/restapi/read")
    public String read(@RequestParam(value = "resourceName", required = false, defaultValue = "0") String resourceName,
                       @RequestParam(value = "rid", required = false, defaultValue = "0") String id) {
        BaseResource resource = null;
        IParser parser = ctx.newJsonParser();
        parser.setPrettyPrint(true);

        if (resourceName.equals("Patient")) {
            try {
                resource = client.read(Patient.class, id);
            } catch (Exception e) {
                return e.toString();
            }
        } else if (resourceName.equals("Observation")) {
            try {
                resource = client.read(Observation.class, id);
            } catch (Exception e) {
                return e.toString();
            }
        }
        return parser.encodeResourceToString(resource);
    }


    // delete
    @RequestMapping(value = "/restapi/delete")
    public String delete(@RequestParam(value = "resourceName", required = false, defaultValue = "0") String resourceName,
                         @RequestParam(value = "did", required = false, defaultValue = "0") String id) {
        BaseOperationOutcome outcome;
        String result = "결과";

        if (resourceName.equals("Patient")) {
            try {
                client.delete().resourceById("Patient", id).execute();
                return "Success";
            } catch (Exception e) {
                return e.toString();
            }
        }
        else if (resourceName.equals("Observation")) {
            try {
                client.delete().resourceById("Observation", id).execute();
                return "Success";
            } catch (Exception e) {
                return e.toString();
            }
        }
        return result;
    }


    //history
    @RequestMapping(value = "/restapi/history")
    public String history(@RequestParam(value = "hid", required = false, defaultValue = "0") String id,
                          @RequestParam(value = "hdate", required = false, defaultValue = "0") String date,
                          @RequestParam(value = "limit", required = false, defaultValue = "0") String limit) {
        String tmp = date;
        String result;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh-mm");
        Date date_final = new Date(tmp);

        try {
            Bundle bundle = client.history().onServer().andReturnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class).since(new InstantDt(date_final)).count(Integer.parseInt(limit)).execute();
            result = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);
        }
        catch(Exception e){
            return e.toString();
        }

        return ("Raw Resource: " + "\n" + result);
    }


    // validate
    @RequestMapping(value = "/restapi/validate")
    public String validate(@RequestParam(value = "inform", required = false) String resource) {
        MethodOutcome outcome;
        OperationOutcome operOutcome;
        String result = null;
        IBaseResource testResource;

        try {
            testResource = (BaseResource) ctx.newJsonParser().parseResource(resource);
            if(testResource.getIdElement().isEmpty()){
                throw new UnprocessableEntityException("Resource has no id");
            }
            else{
                outcome = client.validate(testResource);
                operOutcome = (OperationOutcome) outcome.getOperationOutcome();
                result = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(operOutcome);
            }
        } catch (Exception e) {
            System.out.println(result);
            return e.toString();
        }

        System.out.println(result);
        return (result);
    }
}
