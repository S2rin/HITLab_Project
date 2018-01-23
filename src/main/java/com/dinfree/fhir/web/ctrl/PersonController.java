package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.FhirResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HITLab03 on 2016-08-09.
 */
@Controller
public class  PersonController {

    @Autowired
    FhirResourceLoader ddl;

    @RequestMapping("/personInfo")
    String index(Model model){
        model.addAttribute("pat",ddl.getUser());

        return "personInfo";
    }

}
