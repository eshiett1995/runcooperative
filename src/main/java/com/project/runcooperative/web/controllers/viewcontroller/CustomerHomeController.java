package com.project.runcooperative.web.controllers.viewcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CustomerHomeController {

    @RequestMapping(value = "/customerhome", method = RequestMethod.GET)
    public String ShowHomePage(){

        return "customerhome";
    }
}
