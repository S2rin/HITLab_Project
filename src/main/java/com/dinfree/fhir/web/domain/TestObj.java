package com.dinfree.fhir.web.domain;

/**
 * Created by dinfree on 2016. 8. 4..
 */
public class TestObj {
    private String name;
    private int no;

    public TestObj(String name, int no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public int getNo() {
        return no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
