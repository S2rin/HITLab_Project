package com.dinfree.fhir.web.domain;

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
    List<String> phone;
    GFContactPerson contactPerson;
    String careProvider;
    String careProviderOrganization;

    public GFUser() {
        super();
        this.name = "";
        this.birthdate = "";
        this.gender = "";
        this.address = "";
        this.phone = new ArrayList<String>(){ { add(""); } };
        this.contactPerson = new GFContactPerson(){ { this.name=""; this.phone=""; } };
        this.careProvider = "";
        this.careProviderOrganization = "";
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> phone) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> phone, GFContactPerson contactPerson) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.contactPerson = contactPerson;
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> phone, String careProviderOrganization, String careProvider) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.careProviderOrganization = careProviderOrganization;
        this.careProvider = careProvider;
    }

    public GFUser(String name, String birthdate, String gender, String address, List<String> phone, GFContactPerson contactPerson, String careProvider, String careProviderOrganization) {
        this();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.contactPerson = contactPerson;
        this.careProvider = careProvider;
        this.careProviderOrganization = careProviderOrganization;
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

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public GFContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(GFContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCareProvider() {
        return careProvider;
    }

    public void setCareProvider(String careProvider) {
        this.careProvider = careProvider;
    }

    public String getCareProviderOrganization() {
        return careProviderOrganization;
    }

    public void setCareProviderOrganization(String careProviderOrganization) {
        this.careProviderOrganization = careProviderOrganization;
    }

    @Override
    public String toString() {
        return "GFUser{" +
                "name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone.toString() +
                ", contactPerson=" + contactPerson.toString() +
                ", careProvider='" + careProvider + '\'' +
                ", careProviderOrganization='" + careProviderOrganization + '\'' +
                '}';
    }
}

