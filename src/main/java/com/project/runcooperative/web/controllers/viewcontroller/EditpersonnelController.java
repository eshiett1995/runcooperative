package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.PersonnelEntity;
import com.project.runcooperative.web.entities.Priviledge;
import com.project.runcooperative.web.services.PersonnelService;
import com.project.runcooperative.web.services.PriviledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EditpersonnelController {

    @Autowired
    PersonnelService personnelService;


    @Autowired
    PriviledgeService priviledgeService;

    @RequestMapping(value = "/edit/personnel/{id}", method = RequestMethod.POST)
    public String EditPersonnel(@PathVariable Long id, Model model){

        List<Priviledge> p =  priviledgeService.getAll();

        PersonnelEntity personnelEntity = personnelService.GetPersonnelById(id);

        model.addAttribute("personnel",personnelEntity );

        model.addAttribute("privileges",p);

        return "personneledit";
    }


    @RequestMapping(value = "/edit/personnel/save", method = RequestMethod.POST)
    public String SaveEdit(@ModelAttribute("personnel") PersonnelEntity personnel, Model model, BindingResult result){

        PersonnelEntity personnelEntity = personnelService.GetPersonnelById(personnel.getId());

        personnelEntity.setFirstname(personnel.getFirstname());
        personnelEntity.setLastname(personnel.getLastname());
        personnelEntity.setEmailAddress(personnel.getEmailAddress());
        personnelEntity.setPhoneNumber(personnel.getPhoneNumber());
        personnelEntity.setPassword(personnel.getPassword());
        personnelEntity.setPriviledge(personnel.getPriviledge());
        personnelEntity.setUsername(personnel.getUsername());

       // List<Priviledge> p =  priviledgeService.getAll();

        model.addAttribute("personnels", personnelService.GetAll());

        //model.addAttribute("privileges",p);

        return "personneltable";
    }
}
