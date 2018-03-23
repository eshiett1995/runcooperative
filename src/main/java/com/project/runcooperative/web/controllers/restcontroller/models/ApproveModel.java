package com.project.runcooperative.web.controllers.restcontroller.models;

import org.springframework.stereotype.Component;

/**
 * Created by Oto-obong on 21/03/2018.
 */

@Component
public class ApproveModel {

    private String id;

    private String loanStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}
