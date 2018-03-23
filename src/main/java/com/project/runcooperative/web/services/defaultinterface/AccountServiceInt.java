package com.project.runcooperative.web.services.defaultinterface;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;

public interface AccountServiceInt {

    public void save(AccountEntity accountEntity);

    public AccountEntity getCooperativeAccount();

    public AccountEntity getProcurementAccount();
}
