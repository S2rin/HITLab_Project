package com.dinfree.fhir.web.ctrl;

import com.dinfree.fhir.web.boot.DevDataLoader;
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

    @RequestMapping("/Person")
    String index(Model model){
        model.addAttribute("pat",ddl.getUser());
        return "person";
    }

}
