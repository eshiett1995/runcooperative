package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.LoanEntity;
import com.project.runcooperative.web.repositories.LoanRepository;
import com.project.runcooperative.web.services.defaultinterface.LoanServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class LoanService implements LoanServiceInt {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public void save(LoanEntity loanEntity) {

        loanRepository.save(loanEntity);
    }

    @Override
    public void delete(Long id) {

        loanRepository.delete(id);

    }

    @Override
    public LoanEntity findById(Long id) {

        return loanRepository.findById(id);

        /**List<LoanEntity> loans = new ArrayList<>();

        loans = (List<LoanEntity>) loanRepository.findAll();

        System.out.print("hello moto");

        System.out.print(loans.toString());

        for (LoanEntity loan:loans) {

            if(loan.getId() == id){ return loan;}

            else {return new LoanEntity();}

        }
        return new LoanEntity(); **/
    }

    @Override
    public boolean isEligibleForLoan(Long dateJoined) {

        Long milliseconds = new Date().getTime() - dateJoined;

        long days = (milliseconds / (60*60*24*1000));

        if(days >= 180){

            return  true;
        }
        else{

            return false;
        }
    }

    @Override
    public List<LoanEntity> getAllLoans() {

        return (List<LoanEntity>) loanRepository.findAll();

    }

    @Override
    public List<LoanEntity> getAllLoans(CustomerEntity customerEntity) {

        List<LoanEntity> loans = new ArrayList<>();

        loans = loanRepository.getAllByCustomerEntityOrderByIdDesc(customerEntity);

        if(loans.isEmpty() || loans == null){ return Collections.EMPTY_LIST;}

        else{

            return loans;

        }


    }
}
