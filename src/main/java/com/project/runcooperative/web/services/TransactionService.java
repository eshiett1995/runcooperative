package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.TransactionEntity;
import com.project.runcooperative.web.repositories.TransactionRepository;
import com.project.runcooperative.web.services.defaultinterface.TransactionServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oto-obong on 22/03/2018.
 */


@Service
public class TransactionService implements TransactionServiceInt {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Override
    public void save(TransactionEntity transactionEntity) {

        transactionRepository.save(transactionEntity);

    }

    @Override
    public boolean PerformTransaction(AccountEntity creditAccount, AccountEntity debitAccount, double amount, TransactionEntity.TransactionType transactionType) {

        try {


        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setDate();

        transactionEntity.setCrediting_account(creditAccount);

        transactionEntity.setDebiting_account(debitAccount);

        transactionEntity.setAmount(amount);

        transactionEntity.setTransactionType(transactionType);

        save(transactionEntity);

        return true;

        }catch (Exception ex){


            return false;

        }


    }

    @Override
    public List<TransactionEntity> getAll() {

        return (List<TransactionEntity>) transactionRepository.findAll();
    }
}
