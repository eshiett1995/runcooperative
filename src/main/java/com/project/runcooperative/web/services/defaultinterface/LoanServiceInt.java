package com.project.runcooperative.web.services.defaultinterface;

import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.LoanEntity;

import java.util.List;

public interface LoanServiceInt {

    void save(LoanEntity loanEntity);

    void delete(Long id);

    LoanEntity findById(Long id);

    boolean isEligibleForLoan(Long dateJoined);

    List<LoanEntity> getAllLoans();

    List<LoanEntity> getAllLoans(CustomerEntity customerEntity);


}
