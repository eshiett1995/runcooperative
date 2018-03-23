package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.Loans;
import com.project.runcooperative.web.controllers.restcontroller.models.ResponseModel;
import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.LoanEntity;
import com.project.runcooperative.web.entities.TransactionEntity;
import com.project.runcooperative.web.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmergencyLoanController {

    @Autowired
    AccountService accountService;

    @Autowired
    LoanTypeService loanTypeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    LoanService loanService;

    @Autowired
    TransactionService transactionService;






    @RequestMapping(value = "/loan/emergency", method = RequestMethod.GET)
    public String ShowCustomerTablePage(Model model){

        model.addAttribute("customers", customerService.getAllCustomers() );

        model.addAttribute("issuccessful", "empty");

        return "emergencyloan_customers";

    }



    @RequestMapping(value = "/loan/emergency/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> CreateEmergencyLoan(@RequestBody Loans loansrequest, Model model, BindingResult result){

        //Here, it gets the loan ID...................

        long i = 1;

        if(accountService.getCooperativeAccount().getId() > 0) {

            AccountEntity cooperativeAccount = accountService.getCooperativeAccount();

            CustomerEntity customerEntity = customerService.findCustomerByEmailAndAccoutNumber(loansrequest.getEmailAddress(), loansrequest.getAccountNumber());

            if (customerEntity.getEmailAddress() != null) {

                // check if the amount you want to borrow is more than two of what you have in your account;
                if(loansrequest.getAmount() <= 100000){

                    LoanEntity loanEntity = new LoanEntity();

                    loanEntity.setCustomerEntity(customerEntity);

                    loanEntity.setLoanTypeEntity(loanTypeService.findLoanTypeByID(i));

                    if(cooperativeAccount.getAmount() > loansrequest.getAmount()) {

                        cooperativeAccount.setAmount(cooperativeAccount.getAmount() - loansrequest.getAmount());

                        accountService.save(cooperativeAccount);

                        AccountEntity customerAcc = customerEntity.getAccounts().get(0);

                        customerAcc.setAmount(customerAcc.getAmount() + loansrequest.getAmount());

                        accountService.save(customerAcc);

                        loanEntity.setAmount(loansrequest.getAmount());

                        loanEntity.setLoanStatus("pending");

                        loanEntity.setDuration(loansrequest.getDuration());

                        loanService.save(loanEntity);

                        if(transactionService.PerformTransaction(customerAcc, cooperativeAccount, loansrequest.getAmount(), TransactionEntity.TransactionType.Loan)){

                            ResponseModel r = new ResponseModel();

                            r.setSuccessful(true);

                            r.setResponseMessage("Successful requested");

                            return new ResponseEntity<>(r, HttpStatus.OK);

                        }else {

                            ResponseModel r = new ResponseModel();

                            r.setSuccessful(false);

                            r.setResponseMessage("Error persisting data");

                            return new ResponseEntity<>(r, HttpStatus.OK);

                        }



                    }else{

                        ResponseModel r = new ResponseModel();

                        r.setSuccessful(true);

                        r.setResponseMessage("Cooperative doesn't have up to the amount requested");

                        return new ResponseEntity<>(r, HttpStatus.OK);


                    }

                }else{

                    ResponseModel r = new ResponseModel();

                    r.setSuccessful(true);

                    r.setResponseMessage("The maximum amount you can loan is a N100,000");

                    return new ResponseEntity<>(r, HttpStatus.OK);


                }

            } else {

                ResponseModel r = new ResponseModel();

                r.setSuccessful(true);

                r.setResponseMessage("No customer found");

                return new ResponseEntity<>(r, HttpStatus.OK);

            }

        }else{

            ResponseModel r = new ResponseModel();

            r.setSuccessful(true);

            r.setResponseMessage("there is no cooperative account found please use the populate url to populate all your default entities");

            return new ResponseEntity<>(r, HttpStatus.OK);


        }

    }

    @RequestMapping(value = "/emergencyloan/customer/{id}", method = RequestMethod.POST)
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

        return "loanrequestemergency";

    }
}
