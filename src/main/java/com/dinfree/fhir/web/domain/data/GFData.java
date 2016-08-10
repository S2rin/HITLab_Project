package com.dinfree.fhir.web.domain.data;

/**
 * Created by whitehobbit on 2016. 8. 5..
 */

abstract public class GFData {
    int id;

    public GFData() {
        this.id = 0;
    }

    public GFData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}