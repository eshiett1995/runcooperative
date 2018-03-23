package com.project.runcooperative.web.controllers.restcontroller.models;

import com.project.runcooperative.web.entities.LoanEntity;

import java.util.List;

public class Loans {

    private Long id;
    private String firstname;
    private String lastname;
    private String EmailAddress;
    private String AccountNumber;
    private boolean approve;
    private int Duration;
    private double Amount;
    private LoanEntity loan;
    private List<LoanEntity> loans;

    public Long getId() {
        return id;
    }

    public Loans setId(Long id) {

        this.id = id;

        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public Loans setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Loans setLastname(String lastname) {
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

    public Loans setAccountNumber(String accountNumber) {
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

    public List<LoanEntity> getLoans() {

        return loans;

    }

    public void setLoans(List<LoanEntity> loans) {

        this.loans = loans;

    }

    public LoanEntity getLoan() {
        return loan;
    }

    public void setLoan(LoanEntity loan) {
        this.loan = loan;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}