package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.LoanTypeEntity;
import com.project.runcooperative.web.repositories.LoanTypeRepository;
import com.project.runcooperative.web.services.defaultinterface.LoanTypeServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanTypeService implements LoanTypeServiceInt {

    @Autowired
    LoanTypeRepository loanTypeRepository;

    @Override
    public void save(LoanTypeEntity loanTypeEntity) {

        loanTypeRepository.save(loanTypeEntity);

    }

    @Override
    public LoanTypeEntity findLoanTypeByID(long id) {
        return  loanTypeRepository.findOne(id);
    }
}
