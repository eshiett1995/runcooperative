package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.ResponseModel;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.PersonnelEntity;
import com.project.runcooperative.web.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonneltableController {

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/view/personnel", method = RequestMethod.GET)
    public String ShowPersonnelTablePage(Model model){

        model.addAttribute("personnels", personnelService.GetAll());

        return "personneltable";
    }

    @ResponseBody
    @RequestMapping(value = "/delete/personnel/{personnelId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> DeleteCustomer(@PathVariable Long personnelId, Model model){

        PersonnelEntity personnelEntity = personnelService.GetPersonnelById(personnelId);

        personnelService.delete(personnelId);

        ResponseModel responseModel = new ResponseModel();

        responseModel.setSuccessful(true);

        responseModel.setResponseMessage("the customer " + personnelEntity.getFirstname() + " " + personnelEntity.getLastname() + " has been successfully deleted");

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
