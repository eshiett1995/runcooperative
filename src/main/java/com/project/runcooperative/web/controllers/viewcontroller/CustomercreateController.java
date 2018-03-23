package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.services.AccountNumberService;
import com.project.runcooperative.web.services.AccountService;
import com.project.runcooperative.web.services.CustomerService;
import com.project.runcooperative.web.services.PriviledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomercreateController {

    @Autowired
    AccountService accountService;

    @Autowired
    PriviledgeService priviledgeService;

    @Autowired
    AccountNumberService accountNumberService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String ShowCustomerCreatePage(Model model){

        model.addAttribute("customer", new CustomerEntity());
        model.addAttribute("isSuccessful","empty");

     return "customercreate";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String ShowAddCustomerPage(@ModelAttribute("customer") CustomerEntity customer, Model model, BindingResult result){

    try {


        AccountEntity savingsAccount = new AccountEntity();

        String Token = accountNumberService.generateToken(7);


        savingsAccount.setAccountNumber(accountNumberService.getAccountNumber());
        savingsAccount.setAmount(0);
        savingsAccount.setAccountholder(customer);


        List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();

        accountEntities.add(savingsAccount);
        customer.setDaysjoined(new Date().getTime());
        customer.setAccounts(accountEntities);

        // customer.setPriviledge(priviledgeService.getPriviledge(3));

        if (result.hasErrors())
            result.getAllErrors().toString();


        customerService.save(customer);

        System.out.println("saved");


        accountService.save(savingsAccount);

        model.addAttribute("customer", new CustomerEntity());
        model.addAttribute("isSuccessful", "true");

        return "customercreate";

    }catch (Exception ex){

        System.out.println(ex.getMessage());

        model.addAttribute("customer", new CustomerEntity());

        model.addAttribute("isSuccessful", "false");

        return "customercreate";
    }
  }
}
