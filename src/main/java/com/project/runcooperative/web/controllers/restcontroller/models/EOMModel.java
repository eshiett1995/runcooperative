package com.project.runcooperative.web.controllers.restcontroller.models;

import org.springframework.stereotype.Component;

/**
 * Created by Oto-obong on 25/03/2018.
 */

@Component
public class EOMModel {

    private String outflow;

    private String inflow;

    private String EOMBalance;

    private boolean successful;

    private String responsemessage;

    public String getOutflow() {
        return outflow;
    }

    public EOMModel setOutflow(String outflow) {
        this.outflow = outflow;
        return this;
    }

    public String getInflow() {
        return inflow;
    }

    public EOMModel setInflow(String inflow) {
        this.inflow = inflow;
        return this;
    }

    public String getEOMBalance() {
        return EOMBalance;
    }

    public EOMModel setEOMBalance(String EOMBalance) {
        this.EOMBalance = EOMBalance;
        return this;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public EOMModel setSuccessful(boolean successful) {
        this.successful = successful;
        return this;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public EOMModel setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
        return this;
    }
}
