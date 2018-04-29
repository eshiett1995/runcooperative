package com.project.runcooperative.web.controllers.restcontroller.models;

import com.project.runcooperative.web.entities.LoanEntity;

import java.util.List;

public class Loans2 {

    private Long id;
    private String firstname;
    private String lastname;
    private String EmailAddress;
    private String AccountNumber;
    private boolean approve;
    private int Duration;
    private double Amount;

    public Long getId() {
        return id;
    }

    public Loans2 setId(Long id) {

        this.id = id;

        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public Loans2 setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Loans2 setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public Loans2 setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
        return this;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }


    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}