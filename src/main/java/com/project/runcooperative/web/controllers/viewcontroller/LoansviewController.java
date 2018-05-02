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


public class LoansviewController {
    @Autowired
    LoanService loanService;

    @RequestMapping(value = "/view/loansview", method = RequestMethod.GET)
    public String ShowLoansViewPage(Model model){

        model.addAttribute("loans",loanService.getAllLoans());

        return "loansview";
    }

}

