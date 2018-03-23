package com.project.runcooperative.web.utilities;


import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.LoanEntity;
import com.project.runcooperative.web.entities.TransactionEntity;
import com.project.runcooperative.web.services.AccountService;
import com.project.runcooperative.web.services.CustomerService;
import com.project.runcooperative.web.services.LoanService;
import com.project.runcooperative.web.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    LoanService loanService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss");

    @Scheduled(fixedRate = 10000 )
    public void calculateLoanRepayment() {

        //System.out.println("performing loan calculation "
        //        + dateFormat.format(new Date()));

        List<LoanEntity> loanEntities = loanService.getAllLoans();

        if(loanEntities.size() > 0) {
            for (LoanEntity loan : loanEntities) {

                  if(loan.isHasbeen_paid())
                  {

                      System.out.println("loan has been paid");

                  }else{

                      if (loan.getLoanTypeEntity().getId() == 1) {

                          if (loan.getPayback_amount() > 0) {

                              double interestRate =  loan.getAmount() * 10 * loan.getDuration()/(12 * 100);

                              loan.setPayback_amount(loan.getPayback_amount() - interestRate);

                              AccountEntity cooperativeAccount = accountService.getCooperativeAccount();

                              cooperativeAccount.setAmount(cooperativeAccount.getAmount() + interestRate);

  transactionService.PerformTransaction(cooperativeAccount,loan.getCustomerEntity().getAccounts().get(0),interestRate, TransactionEntity.TransactionType.Loan_Payment);

                              accountService.save(cooperativeAccount);

                          } else {

                              loan.setHasbeen_paid(true);
                          }

                          loanService.save(loan);

                      }else {


                          if (loan.getPayback_amount() > 0) {

                              double q = loan.getDuration();

                              double interestRate =  (loan.getAmount() * 0.01 *(Math.pow(1.01,q)))/( (Math.pow(1.01,q))- 1 );

                              loan.setPayback_amount(loan.getPayback_amount() - interestRate);

                              AccountEntity cooperativeAccount = accountService.getCooperativeAccount();

                              cooperativeAccount.setAmount(cooperativeAccount.getAmount() + interestRate);

                              transactionService.PerformTransaction(loan.getCustomerEntity().getAccounts().get(0),cooperativeAccount,interestRate, TransactionEntity.TransactionType.Loan_Payment);

                              accountService.save(cooperativeAccount);

                          } else {

                              loan.setHasbeen_paid(true);
                          }

                          loanService.save(loan);


                      }


                  }

            }

        }else{

            System.out.println("No loans available");
        }
    }
}
