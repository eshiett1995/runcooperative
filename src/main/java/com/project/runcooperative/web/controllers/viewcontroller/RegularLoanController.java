package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.Loans;
import com.project.runcooperative.web.controllers.restcontroller.models.ResponseModel;
import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.LoanEntity;
import com.project.runcooperative.web.services.AccountService;
import com.project.runcooperative.web.services.CustomerService;
import com.project.runcooperative.web.services.LoanService;
import com.project.runcooperative.web.services.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegularLoanController {

    @Autowired
    AccountService accountService;

    @Autowired
    LoanTypeService loanTypeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    LoanService loanService;


    @RequestMapping(value = "/loan/regular", method = RequestMethod.GET)
    public String ShowCustomersEligibleForLoan(Model model){

        model.addAttribute("customers", customerService.getAllCustomers() );

        model.addAttribute("issuccessful", "empty");

        return "regularloan_customers";
    }

    /**@RequestMapping(value = "/loan/regular", method = RequestMethod.GET)
    public String ShowCustomerTablePage(Model model){

        model.addAttribute("customer", new Loans());

        model.addAttribute("issuccessful", "empty");

        return "loanrequestregular";
    } **/

    @ResponseBody
    @RequestMapping(value = "/loan/regular-add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> CreateRegularLoan(@RequestBody Loans loansrequest, Model model, BindingResult result){

        //Here, it gets the loan ID to indicate the type of loan...................

        long i = 2;

      if(accountService.getCooperativeAccount().getId() > 0) {

          AccountEntity cooperativeAccount = accountService.getCooperativeAccount();

          CustomerEntity customerEntity = customerService.findCustomerByEmailAndAccoutNumber(loansrequest.getEmailAddress(), loansrequest.getAccountNumber());


          if (customerEntity.getEmailAddress() != null) {

              //System.out.println(customerEntity.getEmailAddress());

              if(loanService.isEligibleForLoan(customerEntity.getDaysjoined())) {

                  // check if the amount you want to borrow is more than two of what you have in your account;
                  if (customerEntity.getAccounts().get(0).getAmount() <= loansrequest.getAmount() * 2) {

                      LoanEntity loanEntity = new LoanEntity();

                      loanEntity.setCustomerEntity(customerEntity);

                      loanEntity.setLoanTypeEntity(loanTypeService.findLoanTypeByID(i));

                      if (cooperativeAccount.getAmount() > loansrequest.getAmount()) {

                          cooperativeAccount.setAmount(cooperativeAccount.getAmount() - loansrequest.getAmount());

                          accountService.save(cooperativeAccount);

                          loanEntity.setAmount(loansrequest.getAmount());

                          loanEntity.setLoanStatus("pending");

                          loanEntity.setDuration(loansrequest.getDuration());

                          loanService.save(loanEntity);

                          ResponseModel r = new ResponseModel();

                          r.setSuccessful(true);

                          r.setResponseMessage("Successful requested");

                          return new ResponseEntity<>(r, HttpStatus.OK);

                      } else {

                          ResponseModel r = new ResponseModel();

                          r.setSuccessful(false);

                          r.setResponseMessage("Cooperative doesn't have up to the amount requested");

                          return new ResponseEntity<>(r, HttpStatus.OK);


                      }

                  } else {

                      ResponseModel r = new ResponseModel();

                      r.setSuccessful(false);

                      r.setResponseMessage("you do not have enough in your account, you have " + customerEntity.getAccounts().get(0).getAmount());

                      return new ResponseEntity<>(r, HttpStatus.OK);


                  }

              }else{

                  ResponseModel r = new ResponseModel();

                  r.setSuccessful(false);

                  r.setResponseMessage("you aren't eligible for a loan");

                  return new ResponseEntity<>(r, HttpStatus.OK);



              }

          } else{

                  ResponseModel r = new ResponseModel();

                  r.setSuccessful(false);

                  r.setResponseMessage("No customer found");

                  return new ResponseEntity<>(r, HttpStatus.OK);

              }

    }else{

          ResponseModel r = new ResponseModel();

          r.setSuccessful(false);

          r.setResponseMessage("there is no cooperative account found please use the populate url to populate all your default entities");

          return new ResponseEntity<>(r, HttpStatus.OK);
    }


    }

    @RequestMapping(value = "/regularloan/customer/{id}", method = RequestMethod.POST)
    public String RequestRegularLoan(@PathVariable Long id, Model model){

        CustomerEntity customerEntity = customerService.getCustomerById(id);

        Loans loans = new Loans();

        loans.setId(customerEntity.getId())
                .setFirstname(customerEntity.getFirstname())
                .setLastname(customerEntity.getLastname())
                .setAccountNumber(customerEntity.getAccounts().get(0).getAccountNumber())
                .setEmailAddress(customerEntity.getEmailAddress());


        model.addAttribute("customer", loans);

        model.addAttribute("issuccessful", "empty");

        return "loanrequestregular";

    }
}