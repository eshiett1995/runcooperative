package com.project.runcooperative.web.controllers.restcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.*;
import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.TransactionEntity;
import com.project.runcooperative.web.services.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.NestedTransactionNotSupportedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Oto-obong on 22/03/2018.
 */




@RestController
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/transaction/purchase", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> PurchaseGood(@RequestBody TransactionModel transactionEntity) {


      AccountEntity cooperativeAccount = accountService.getCooperativeAccount();

      if(cooperativeAccount.getAmount() < transactionEntity.getAmount()){

          ResponseModel responseModel = new ResponseModel();

          responseModel.setSuccessful(false);

          responseModel.setResponseMessage("Co-operative account does not have sufficient fund");

          return new ResponseEntity<>(responseModel, HttpStatus.OK);


      }else{

          cooperativeAccount.setAmount(cooperativeAccount.getAmount()-transactionEntity.getAmount());

          accountService.save(cooperativeAccount);

          AccountEntity procurementAccount = accountService.getProcurementAccount();

          procurementAccount.setAmount(procurementAccount.getAmount() + transactionEntity.getAmount());

          accountService.save(procurementAccount);

          TransactionEntity transaction = new TransactionEntity();

          transaction.setDate();

          transaction.setDebiting_account(cooperativeAccount);

          transaction.setTransactionType(TransactionEntity.TransactionType.Purchases);

          transaction.setCrediting_account(procurementAccount);

          transaction.setAmount(transactionEntity.getAmount());

          transaction.setDetails(transaction.getDetails());

          transactionService.save(transaction);

          ResponseModel responseModel = new ResponseModel();

          responseModel.setSuccessful(true);

          responseModel.setResponseMessage("Success");

          return new ResponseEntity<>(responseModel, HttpStatus.OK);

      }



    }

    @RequestMapping(value = "/transaction/sales", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> SellGoods(@RequestBody TransactionModel transactionEntity) {


        AccountEntity cooperativeAccount = accountService.getCooperativeAccount();


            cooperativeAccount.setAmount(cooperativeAccount.getAmount()+ transactionEntity.getAmount());

            accountService.save(cooperativeAccount);

            AccountEntity procurementAccount = accountService.getProcurementAccount();

            //procurementAccount.setAmount(procurementAccount.getAmount() + transactionEntity.getAmount());

            accountService.save(procurementAccount);

            TransactionEntity transaction = new TransactionEntity();

            transaction.setDate();

            transaction.setDebiting_account(procurementAccount);

            transaction.setTransactionType(TransactionEntity.TransactionType.Purchases);

            transaction.setCrediting_account(cooperativeAccount);

            transaction.setAmount(transactionEntity.getAmount());

            transaction.setDetails(transaction.getDetails());

            transactionService.save(transaction);

        ResponseModel responseModel = new ResponseModel();

        responseModel.setSuccessful(true);

        responseModel.setResponseMessage("Success");

        return new ResponseEntity<>(responseModel, HttpStatus.OK);


    }

    @RequestMapping(value = "/transaction/eom", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EOMModel> EOM(@RequestBody DateModel dateModel) {

        double inflow = 0.00;

        double outflow = 0.00;

        List<TransactionEntity> transactionsForTheMonth = new ArrayList<>();

        List<TransactionEntity> transactionEntities = new ArrayList<>();

        transactionEntities = transactionService.getAll();

        if (transactionEntities.isEmpty()) {

            EOMModel eomModel = new EOMModel();

            eomModel.setInflow("0").setOutflow(String.valueOf("0")).setSuccessful(false).setResponsemessage("No transaction has been found")
                    .setEOMBalance("0");

            return new ResponseEntity<>(eomModel, HttpStatus.OK);

        } else {

            for (TransactionEntity transaction : transactionEntities) {

                Calendar cal = Calendar.getInstance();
                cal.setTime(transaction.getDate());
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);

                if (dateModel.getMonth() == month && dateModel.getYear() == year) {

                    transactionsForTheMonth.add(transaction);

                }


            }

            if(transactionsForTheMonth.isEmpty()){

                EOMModel eomModel = new EOMModel();

                eomModel.setInflow("0").setOutflow(String.valueOf("0")).setSuccessful(false).setResponsemessage("No transaction occurred this month")
                        .setEOMBalance("0");

                return new ResponseEntity<>(eomModel, HttpStatus.OK);

            }else{


                for (TransactionEntity transactionIntheMonth:transactionsForTheMonth) {

                    if(transactionIntheMonth.getTransactionType() == TransactionEntity.TransactionType.Loan || transactionIntheMonth.getTransactionType() == TransactionEntity.TransactionType.Purchases )
                    {

                        outflow = outflow + transactionIntheMonth.getAmount();

                    }else{

                        inflow = inflow + transactionIntheMonth.getAmount();

                    }
                }

                EOMModel eomModel = new EOMModel();

                eomModel.setInflow(String.valueOf(inflow)).setOutflow(String.valueOf(outflow)).setSuccessful(true).setResponsemessage("Successful")
                        .setEOMBalance(inflow > outflow ? String.valueOf(inflow - outflow) : "-" + String.valueOf(outflow - inflow) );

                return new ResponseEntity<>(eomModel, HttpStatus.OK);

            }

        }
    }



    @RequestMapping(value = "/transaction/pnl", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PNLModel> pnl(@RequestBody DateModel dateModel) {

        double inflow = 0.00;

        double outflow = 0.00;

        List<TransactionEntity> transactionsForTheMonth = new ArrayList<>();

        List<TransactionEntity> transactionEntities = new ArrayList<>();

        transactionEntities = transactionService.getAll();

        if (transactionEntities.isEmpty()) {

            EOMModel eomModel = new EOMModel();

            eomModel.setInflow("0").setOutflow(String.valueOf("0")).setSuccessful(false).setResponsemessage("No transaction has been found")
                    .setEOMBalance("0");

            return new ResponseEntity<>(new PNLModel(), HttpStatus.OK);

        } else {

            for (TransactionEntity transaction : transactionEntities) {

                Calendar cal = Calendar.getInstance();
                cal.setTime(transaction.getDate());
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);

                if (dateModel.getMonth() == month && dateModel.getYear() == year) {

                    transactionsForTheMonth.add(transaction);

                }


            }

            if(transactionsForTheMonth.isEmpty()){

                EOMModel eomModel = new EOMModel();

                eomModel.setInflow("0").setOutflow(String.valueOf("0")).setSuccessful(false).setResponsemessage("No transaction occurred this month")
                        .setEOMBalance("0");

                return new ResponseEntity<>(new PNLModel(), HttpStatus.OK);

            }else{


                for (TransactionEntity transactionIntheMonth:transactionsForTheMonth) {

                    if(transactionIntheMonth.getTransactionType() == TransactionEntity.TransactionType.Loan || transactionIntheMonth.getTransactionType() == TransactionEntity.TransactionType.Purchases )
                    {

                        outflow = outflow + transactionIntheMonth.getAmount();

                    }else{

                        inflow = inflow + transactionIntheMonth.getAmount();

                    }
                }

                EOMModel eomModel = new EOMModel();

                eomModel.setInflow(String.valueOf(inflow)).setOutflow(String.valueOf(outflow)).setSuccessful(true).setResponsemessage("Successful")
                        .setEOMBalance(inflow > outflow ? String.valueOf(inflow - outflow) : "-" + String.valueOf(outflow - inflow) );

                return new ResponseEntity<>(new PNLModel(), HttpStatus.OK);

            }

        }
    }

}

