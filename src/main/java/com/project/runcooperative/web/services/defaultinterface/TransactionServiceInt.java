package com.project.runcooperative.web.services.defaultinterface;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.TransactionEntity;

import java.util.List;

/**
 * Created by Oto-obong on 22/03/2018.
 */
public interface TransactionServiceInt {

    void save(TransactionEntity transactionEntity);

    boolean PerformTransaction(AccountEntity creditAccount,AccountEntity debitAccount, double amount, TransactionEntity.TransactionType transactionType );

    List<TransactionEntity> getAll();
}
