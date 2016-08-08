package com.dinfree.fhir.web.domain;

public class GFContactPerson {
    String name;
    String phone;

    public GFContactPerson() {
        this.name = "";
        this.phone = "";
    }

    public GFContactPerson(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
