package com.dinfree.fhir.web.domain.data.user;

import com.dinfree.fhir.web.domain.data.GFData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whitehobbit on 2016. 8. 4..
 */
@Data
public class GFUser extends GFData {
    String name;
    String birthdate;
    String gender;
    String address;
    List<String> telecom;
    GFContact contact;
    String careProvider;
    String managingOrganization;

    public GFUser() {
        super();
        this.name = "";
        this.birthdate = "";
        this.gender = "";
        this.address = "";
        this.telecom = new ArrayList<String>(){ { add(""); } };
        this.contact = new GFContact(){ { this.name=""; this.telecom=""; } };
        this.careProvider = "";
        this.managingOrganization = "";
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> telecom) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.telecom = telecom;
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> telecom, GFContact contact) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.telecom = telecom;
        this.contact = contact;
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> telecom, String managingOrganization, String careProvider) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.telecom = telecom;
        this.managingOrganization = managingOrganization;
        this.careProvider = careProvider;
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> telecom, GFContact contact, String careProvider, String managingOrganization) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.telecom = telecom;
        this.contact = contact;
        this.careProvider = careProvider;
        this.managingOrganization = managingOrganization;
    }
}

