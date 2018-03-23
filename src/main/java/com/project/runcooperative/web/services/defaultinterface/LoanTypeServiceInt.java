package com.project.runcooperative.web.services.defaultinterface;

import com.project.runcooperative.web.entities.LoanTypeEntity;

public interface LoanTypeServiceInt {

    public void save(LoanTypeEntity loanTypeEntity);

    public LoanTypeEntity findLoanTypeByID(long id);
}
