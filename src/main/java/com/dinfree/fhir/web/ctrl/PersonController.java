package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.DevDataLoader;
import com.dinfree.fhir.web.component.Person;
import com.dinfree.fhir.web.domain.data.user.GFUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by HITLab03 on 2016-08-09.
 */
@Controller
public class PersonController {
    @Autowired DevDataLoader ddl;
    @Autowired Person person;

    @RequestMapping("/Person")
    String index(Model model){
        GFUser pat = ddl.getUser();
        person.setPerson(pat);
        return "person";
    }

    @ModelAttribute("person")
    public GFUser getPerson(){
        return person.getPerson();
    }
}
