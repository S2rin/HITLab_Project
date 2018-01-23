package com.dinfree.fhir.web.boot;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.IGenericClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by whitehobbit on 2016. 8. 11..
 */

@Data
@AllArgsConstructor
public class FhirConnector {

    private String serverBaseUrl;
    private FhirContext ctx;
    private IGenericClient client;
}
