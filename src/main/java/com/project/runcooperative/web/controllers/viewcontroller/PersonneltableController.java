package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonneltableController {

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/view/personnel", method = RequestMethod.GET)
    public String ShowPersonnelTablePage(Model model){

        model.addAttribute("personnels", personnelService.GetAll());

        return "personneltable";
    }
}
