package com.dinfree.fhir.web.domain.data.user;

import lombok.Data;

@Data
public class GFContact {
    String name;
    String telecom;

    public GFContact() {
        this.name = "";
        this.telecom = "";
    }

    public GFContact(String name, String phone) {
        this.name = name;
        this.telecom = phone;
    }
}
