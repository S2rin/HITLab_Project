package com.dinfree.fhir.web.boot;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.IGenericClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by whitehobbit on 2016. 9. 12..
 */

@NoArgsConstructor
public class FhirServerConnectionMaker {
    @Getter IGenericClient client;

    public FhirConnector makeConnection() {
        String serverBaseUrl = "http://hitlab.gachon.ac.kr:8888/gachon-fhir-server/baseDstu2/";
        FhirContext fhirContext = FhirContext.forDstu2();
        client = fhirContext.newRestfulGenericClient(serverBaseUrl);
        return new FhirConnector(serverBaseUrl, fhirContext, client);
    }

    public FhirConnector makeConnection(String fhirServerBaseUrl) {
        String serverBaseUrl = fhirServerBaseUrl;
        FhirContext fhirContext = FhirContext.forDstu2();
        client = fhirContext.newRestfulGenericClient(serverBaseUrl);
        return new FhirConnector(serverBaseUrl, fhirContext, client);
    }
}
