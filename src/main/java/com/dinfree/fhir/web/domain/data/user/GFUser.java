package com.dinfree.fhir.web.domain.data.user;

import com.dinfree.fhir.web.domain.data.GFData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whitehobbit on 2016. 8. 4..
 */

public class GFUser extends GFData {
    String name;
    String birthdate;
    String gender;
    String address;
    List<String> telecom;
    GFRelationship relationship;
    String careProvider;
    String managingOrganization;

    public GFUser() {
        super();
        this.name = "";
        this.birthdate = "";
        this.gender = "";
        this.address = "";
        this.telecom = new ArrayList<String>(){ { add(""); } };
        this.relationship = new GFRelationship(){ { this.name=""; this.telecom=""; } };
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

    public GFUser(String name, String birthdate, String gender, String address, List<String> telecom, GFRelationship relationship) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.telecom = telecom;
        this.relationship = relationship;
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

    public GFUser(String name, String birthdate, String gender, String address, List<String> telecom, GFRelationship relationship, String careProvider, String managingOrganization) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.telecom = telecom;
        this.relationship = relationship;
        this.careProvider = careProvider;
        this.managingOrganization = managingOrganization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<String> telecom) {
        this.telecom = telecom;
    }

    public GFRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(GFRelationship relationship) {
        this.relationship = relationship;
    }

    public String getCareProvider() {
        return careProvider;
    }

    public void setCareProvider(String careProvider) {
        this.careProvider = careProvider;
    }

    public String getManagingOrganization() {
        return managingOrganization;
    }

    public void setManagingOrganization(String managingOrganization) {
        this.managingOrganization = managingOrganization;
    }

    @Override
    public String toString() {
        return "GFUser{" +
                "name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", telecom=" + telecom.toString() +
                ", relationship=" + relationship.toString() +
                ", careProvider='" + careProvider + '\'' +
                ", managingOrganization='" + managingOrganization + '\'' +
                '}';
    }
}

