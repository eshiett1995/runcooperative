package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.repositories.AccountRepository;
import com.project.runcooperative.web.services.defaultinterface.AccountNumberServiceInt;
import com.project.runcooperative.web.services.defaultinterface.AccountServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService implements AccountServiceInt {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void save(AccountEntity accountEntity) {

        accountRepository.save(accountEntity);

    }

    @Override
    public AccountEntity getCooperativeAccount() {

        AccountEntity accountEntity = new AccountEntity();

        List<AccountEntity> accountEntities = new ArrayList<>();
        accountEntities = (List<AccountEntity>) accountRepository.findAll();
        for (AccountEntity acc: accountEntities) {
            if (acc.getAccountNumber().equalsIgnoreCase("0000"))
                accountEntity = acc;
        }

        return accountEntity;
    }

    @Override
    public AccountEntity getProcurementAccount() {

        AccountEntity accountEntity = new AccountEntity();

        List<AccountEntity> accountEntities = new ArrayList<>();
        accountEntities = (List<AccountEntity>) accountRepository.findAll();
        for (AccountEntity acc: accountEntities) {
            if (acc.getAccountNumber().equalsIgnoreCase("1111"))
                accountEntity = acc;
        }

        return accountEntity;
    }


}
