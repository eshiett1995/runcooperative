package com.project.runcooperative.web.controllers.restcontroller.models;

import org.springframework.stereotype.Component;

/**
 * Created by Oto-obong on 22/03/2018.
 */

@Component
public class TransactionModel {

    private String date;

    private double amount;

    private String details;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
