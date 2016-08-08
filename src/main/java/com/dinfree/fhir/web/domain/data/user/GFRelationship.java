package com.dinfree.fhir.web.domain.data.user;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelecom() {
        return telecom;
    }

    public void setTelecom(String phone) {
        this.telecom = phone;
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "name='" + name + '\'' +
                ", telecom='" + telecom + '\'' +
                '}';
    }
}
