package com.dinfree.fhir.web.component;

import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by HITLab03 on 2016-08-09.
 */
@Component
public class Person {
    private GFUser person = new GFUser();

    public Person(){
    }

    public GFUser getPerson(){
        return person;
    }

    public void setPerson(GFUser person){
        this.person = person;
    }
}
