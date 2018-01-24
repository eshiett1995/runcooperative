package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditpersonnelController {

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/edit/personnel", method = RequestMethod.GET)
    public String ShowEditUserTablePage(){

        return "editpersonnel";
    }
}
