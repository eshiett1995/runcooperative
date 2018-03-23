package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EditcustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.POST)
    public String ShowEditCustomerTablePage(@PathVariable Long id, Model model){

        CustomerEntity customerEntity = customerService.getCustomerById(id);

        model.addAttribute("customer", customerEntity);

        return "customeredit";
    }

    @RequestMapping(value = "/edit/save", method = RequestMethod.POST)
    public String SaveEdit(@ModelAttribute("customer") CustomerEntity customer, Model model, BindingResult result){

        CustomerEntity customerEntity = customerService.getCustomerById(customer.getId());

        customerEntity.setFirstname(customer.getFirstname());
        customerEntity.setLastname(customer.getLastname());
        customerEntity.setEmailAddress(customer.getEmailAddress());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setSalary(customer.getSalary());
        customerEntity.setFixedRate(customer.getFixedRate());

        customerService.save(customerEntity);

        List<CustomerEntity> customerEntities = new ArrayList<>();

        customerEntities = customerService.getAllCustomers();

        model.addAttribute("customers", customerEntities);

        return "customertable";
    }
}
