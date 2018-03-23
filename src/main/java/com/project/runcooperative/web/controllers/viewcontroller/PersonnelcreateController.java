package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.entities.Enum;
import com.project.runcooperative.web.entities.Enum.Priviledges;
import com.project.runcooperative.web.entities.PersonnelEntity;
import com.project.runcooperative.web.entities.Priviledge;
import com.project.runcooperative.web.services.PersonnelService;
import com.project.runcooperative.web.services.PriviledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/personnel")
public class PersonnelcreateController {

    @Autowired
    PersonnelService personnelService;

    @Autowired
    PriviledgeService priviledgeService;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String ShowPersonnelCreatePage(Model model){



       List<Priviledge> p =  priviledgeService.getAll();

        for (Priviledge pp: p
             ) {
           System.out.println(pp.getName());
        }

        model.addAttribute("personnel", new PersonnelEntity());

        List<String> priviledges = Collections.EMPTY_LIST;

       // priviledges.add(priviledgeService.getPriviledge(1).getName());

        //priviledges.add(priviledgeService.getPriviledge(2).getName());

        //priviledges.add(priviledgeService.getPriviledge(3).getName());


        model.addAttribute("privileges",p);
      //  model.addAttribute("privileges1",priviledgeService.getPriviledge(1));
      //  model.addAttribute("privileges2",priviledgeService.getPriviledge(2));
       // model.addAttribute("privileges3",priviledgeService.getPriviledge(3));

        return "personnelcreate";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String ShowAddCustomerPage(@ModelAttribute("personnel") PersonnelEntity personnel, Model model, BindingResult result){


        if(result.hasErrors())
            result.getAllErrors().toString();

        personnelService.save(personnel);
        System.out.println("saved");

        model.addAttribute("personnel", new PersonnelEntity());

        return "personnelcreate";
    }
}
