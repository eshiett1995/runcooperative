package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.controllers.restcontroller.models.ApproveModel;
import com.project.runcooperative.web.controllers.restcontroller.models.Loans;
import com.project.runcooperative.web.controllers.restcontroller.models.ResponseModel;
import com.project.runcooperative.web.entities.LoanEntity;
import com.project.runcooperative.web.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoanstableController {

    @Autowired
    LoanService loanService;

    @RequestMapping(value = "/view/loans", method = RequestMethod.GET)
    public String ShowLoansTablePage(Model model){

        model.addAttribute("loans",loanService.getAllLoans());

        return "loanstable";
    }


    @ResponseBody
    @RequestMapping(value = "/view/loans/{id}", method = RequestMethod.GET)
    public ResponseEntity<LoanEntity> ShowLoanStatus(@PathVariable long id){

       LoanEntity loanEntity = loanService.findById(id);

        Loans loans = new Loans();

        loans.setLoan(loanEntity);

        return new ResponseEntity<>(loanEntity, HttpStatus.OK);

    }


    @ResponseBody
    @RequestMapping(value = "/approve/loans", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> ApproveLoan(@RequestBody ApproveModel loan){

        LoanEntity loanEntity = loanService.findById(Long.valueOf(loan.getId()));

        if(loan.getLoanStatus().equalsIgnoreCase("declined")){

            loanService.delete(loanEntity.getId());


        }else {

            loanEntity.setLoanStatus(loan.getLoanStatus());

            loanService.save(loanEntity);
        }

        ResponseModel responseModel = new ResponseModel();

        responseModel.setSuccessful(true);

        responseModel.setResponseMessage("Successful");

        return new ResponseEntity<>(responseModel, HttpStatus.OK);

    }
}

