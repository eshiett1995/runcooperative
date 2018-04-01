package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.ResponseModel;
import com.project.runcooperative.web.controllers.restcontroller.models.TransactionModel;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.TransactionEntity;
import com.project.runcooperative.web.services.AccountService;
import com.project.runcooperative.web.services.CustomerService;
import com.project.runcooperative.web.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomertableController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

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

    @RequestMapping(value = "/customer/transaction", method = RequestMethod.GET)
    public String CustomerTransaction(Model model){

        List<CustomerEntity> customerEntities = new ArrayList<>();



        customerEntities = customerService.getAllCustomers();

        for (CustomerEntity c:customerEntities) {

            System.out.println(c.getAccounts().size());
        }

        model.addAttribute("customers", customerEntities);

        return "customertransaction";
    }

    @RequestMapping(value = "/transaction/deposit", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> CustomerDeposit(@RequestBody TransactionModel transactionModel) {

        try {

            CustomerEntity customerEntity = customerService.getCustomerById(Long.parseLong(transactionModel.getId()));

            TransactionEntity transactionEntity = new TransactionEntity();

            transactionEntity.setTransactionType(TransactionEntity.TransactionType.Deposit);

            transactionEntity.setAmount(transactionModel.getAmount());

            transactionEntity.setCrediting_account(customerEntity.getAccounts().get(0));

            transactionEntity.setDate();

            transactionEntity.setDebiting_account(accountService.getCooperativeAccount()); // this is just a holder, it is really not the debiting account

            transactionService.save(transactionEntity);

            ResponseModel responseModel = new ResponseModel();

            responseModel.setSuccessful(true);

            responseModel.setResponseMessage("The Amount of N" + transactionModel.getAmount() +

                    ", was successfully deposited into " + customerEntity.getFirstname() + " " +

                    customerEntity.getLastname() + "'s account");

            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }catch (Exception ex){

            ResponseModel responseModel = new ResponseModel();

            responseModel.setSuccessful(false);

            responseModel.setResponseMessage("Please try again, a connection error has occurred.");

            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }

    }
}
