package com.project.runcooperative.web.controllers.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoanstypeController {

    @RequestMapping(value = "/loans/type", method = RequestMethod.GET)
    public String ShowLoansTypePage(){

        return "loanstype";
    }
}
