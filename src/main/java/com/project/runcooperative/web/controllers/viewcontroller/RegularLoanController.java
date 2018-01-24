package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.Loans;
import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.LoanEntity;
import com.project.runcooperative.web.services.AccountService;
import com.project.runcooperative.web.services.CustomerService;
import com.project.runcooperative.web.services.LoanService;
import com.project.runcooperative.web.services.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegularLoanController {

    @Autowired
    AccountService accountService;

    @Autowired
    LoanTypeService loanTypeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    LoanService loanService;

    @RequestMapping(value = "/loan/regular", method = RequestMethod.GET)
    public String ShowCustomerTablePage(Model model){

        model.addAttribute("customer", new Loans());

        model.addAttribute("issuccessful", "empty");

        return "loanrequestregular";
    }

    @RequestMapping(value = "/loan/regular-add", method = RequestMethod.POST)
    public String CreateRegularLoan(@ModelAttribute("customer") Loans loansrequest, Model model, BindingResult result){

        //Here, it gets the loan ID...................

        long i = 2;

      if(accountService.getCooperativeAccount().getId() > 0) {

          AccountEntity cooperativeAccount = accountService.getCooperativeAccount();

          CustomerEntity customerEntity = customerService.findCustomerByEmailAndAccoutNumber(loansrequest.getEmailAddress(), loansrequest.getAccountNumber());


          if (customerEntity.getEmailAddress() != null) {

              System.out.println(customerEntity.getEmailAddress());

              if(loanService.isEligibleForLoan(customerEntity.getDaysjoined())) {

                  // check if the amount you want to borrow is more than two of what you have in your account;
                  if (customerEntity.getAccounts().get(0).getAmount() <= loansrequest.getAmount() * 2) {

                      LoanEntity loanEntity = new LoanEntity();

                      loanEntity.setCustomerEntity(customerEntity);

                      loanEntity.setLoanTypeEntity(loanTypeService.findLoanTypeByID(i));

                      if (cooperativeAccount.getAmount() > loansrequest.getAmount()) {

                          cooperativeAccount.setAmount(cooperativeAccount.getAmount() - loansrequest.getAmount());

                          accountService.save(cooperativeAccount);

                          loanEntity.setAmount(loansrequest.getAmount());

                          loanEntity.setDuration(loansrequest.getDuration());

                          loanService.save(loanEntity);

                          model.addAttribute("customer", new LoanEntity());

                          model.addAttribute("isSuccessful", "true");

                      } else {

                          System.out.println("Cooperative doesn't have up to the amount requested");

                          model.addAttribute("customer", new LoanEntity());

                          model.addAttribute("isSuccessful", "Cooperative balance not enough");

                      }

                  } else {

                      System.out.println("you do not have enough in your account, you have " + customerEntity.getAccounts().get(0).getAmount());

                      String s = "you do not have enough in your account, you have " + customerEntity.getAccounts().get(0).getAmount();

                      model.addAttribute("customer", new LoanEntity());

                      model.addAttribute("isSuccessful", "you do not have enough in your account");

                      model.addAttribute("message", "you do not have enough in your account, you have " + customerEntity.getAccounts().get(0).getAmount());


                  }

              }else{

                  System.out.println("you arent eligible for a loan");

                  model.addAttribute("customer", new LoanEntity());

                  model.addAttribute("isSuccessful", "you arent eligible for a loan");


              }

              } else{

                  System.out.println("No customer found");

                  model.addAttribute("customer", new LoanEntity());

                  model.addAttribute("isSuccessful", "No customer found");

                  model.addAttribute("message", "No customer found");
              }


      }else{

          System.out.println("there is no cooperative account found please use the populate url to populate all your default entities");

          model.addAttribute("customer", new LoanEntity());

          model.addAttribute("isSuccessful", "Cooperative account not existing");
      }

        model.addAttribute("customer", new Loans());


        return "loanrequestregular";


    }
}