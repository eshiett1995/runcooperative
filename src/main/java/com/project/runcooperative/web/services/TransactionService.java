package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.TransactionEntity;
import com.project.runcooperative.web.repositories.TransactionRepository;
import com.project.runcooperative.web.services.defaultinterface.TransactionServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Oto-obong on 22/03/2018.
 */


@Service
public class TransactionService implements TransactionServiceInt {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void save(TransactionEntity transactionEntity) {

        transactionRepository.save(transactionEntity);

    }
}
