package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.Loans;
import com.project.runcooperative.web.controllers.restcontroller.models.PasswordModel;
import com.project.runcooperative.web.controllers.restcontroller.models.ResponseModel;
import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.Enum;
import com.project.runcooperative.web.repositories.CustomerRepository;
import com.project.runcooperative.web.services.CustomerService;
import com.project.runcooperative.web.services.DefaultCookieService;
import com.project.runcooperative.web.services.LoanService;
import com.project.runcooperative.web.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class LoginCustomerController {


    @Autowired
    LoanService loanService;

    @Autowired
    DefaultCookieService defaultCookieService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customerlogin", method = RequestMethod.GET)
    public String ShowLoginPage(Model model) {

        model.addAttribute("customer", new CustomerEntity());

        return "logincustomer";
    }

    @RequestMapping(value = "/login/authenticatecustomer", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> AuthenticateLogin(@RequestBody CustomerEntity customerEntity, Model model, BindingResult result, HttpServletRequest req, HttpServletResponse response) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
        }

        if (customerService.AuthenticateCustomer(customerEntity.getEmailAddress(), customerEntity.getPassword())) {

            CustomerEntity customer = customerService.getCustomerByEmail(customerEntity.getEmailAddress());

            HttpSession session = req.getSession(true);

            session.setAttribute(session.getId(), customerEntity.getEmailAddress());

            System.out.print(session.getId());

            response.addCookie(defaultCookieService.generateCookie(session.getId()));

            ResponseModel r = new ResponseModel();

            r.setSuccessful(true);

            r.setResponseMessage(customer.getEmailAddress());

            r.setFullname(customer.getFirstname() + " " + customer.getLastname());

            return new ResponseEntity<>(r, HttpStatus.OK);


        } else {


            ResponseModel r = new ResponseModel();
            r.setSuccessful(false);
            r.setResponseMessage("Email or password is invalid");

            return new ResponseEntity<>(r, HttpStatus.OK);


        }


    }

    @RequestMapping(value = "/customer-home", method = RequestMethod.GET)
    public String GoHome(HttpServletRequest req, HttpServletResponse response) {

        return "customerhome";

    }


    @RequestMapping(value = "/customer/change-password", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> ChangePassword(@RequestBody PasswordModel passwordModel, HttpServletRequest req, HttpServletResponse response) {

        CustomerEntity customer = customerService.getCustomerByEmail(passwordModel.getEmail());

        if(customer.getPassword().equals(passwordModel.getOldPassword())){

            customer.setPassword(passwordModel.getNewPassword());

            customerService.save(customer);

            ResponseModel r = new ResponseModel();

            r.setSuccessful(true);

            r.setResponseMessage("Successfully updated");

            return new ResponseEntity<>(r, HttpStatus.OK);

        }else{

            ResponseModel r = new ResponseModel();

            r.setSuccessful(false);

            r.setResponseMessage("Password Incorrect");

            return new ResponseEntity<>(r, HttpStatus.OK);

        }

    }


    @RequestMapping(value = "/{email}/loans", method = RequestMethod.GET)
    public ResponseEntity<Loans> ChangePassword(@PathVariable String email, HttpServletRequest req, HttpServletResponse response) {

        CustomerEntity customerEntity = customerService.getCustomerByEmail(email);

        Loans loans = new Loans();

        loans.setLoans(loanService.getAllLoans(customerEntity));

       return new ResponseEntity<>(loans, HttpStatus.OK);

    }
 }