package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomertableController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/view/customer", method = RequestMethod.GET)
    public String ShowCustomerTablePage(Model model){

        List<CustomerEntity> customerEntities = new ArrayList<>();



        customerEntities = customerService.getAllCustomers();

        for (CustomerEntity c:customerEntities) {

            System.out.println(c.getAccounts().size());
        }

        model.addAttribute("customers", customerEntities);

        return "customertable";
    }
}
