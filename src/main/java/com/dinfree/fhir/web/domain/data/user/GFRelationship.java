package com.dinfree.fhir.web.domain.data.user;

import lombok.Data;

@Data
public class GFRelationship {
    String name;
    String telecom;

    public GFRelationship() {
        this.name = "";
        this.telecom = "";
    }

    public GFRelationship(String name, String phone) {
        this.name = name;
        this.telecom = phone;
    }
}
