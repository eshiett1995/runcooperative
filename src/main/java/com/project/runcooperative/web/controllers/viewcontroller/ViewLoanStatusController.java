package com.project.runcooperative.web.controllers.viewcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ViewLoanStatusController {

    @RequestMapping(value = "/view/loanstatus", method = RequestMethod.GET)
    public String ShowViewLoanStatusPage(){

        return "viewloanstatus";
    }
}
