package com.project.runcooperative.web.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class TransactionEntity{

    public enum TransactionType { Purchases, Sales,Loan,Loan_Payment}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    private Date date;

    @ManyToOne(targetEntity = AccountEntity.class)
    @JoinColumn(name="debiting_account")
    @JsonManagedReference(value = "debiting_account")
    private AccountEntity debiting_account;

    @ManyToOne(targetEntity = AccountEntity.class)
    @JoinColumn(name="crediting_account")
    @JsonManagedReference(value = "crediting_account")
    private AccountEntity crediting_account;

    private double amount;

    private String details;

    private TransactionType transactionType;

    public AccountEntity getDebiting_account() {
        return debiting_account;
    }

    public void setDebiting_account(AccountEntity debiting_account) {
        this.debiting_account = debiting_account;
    }

    public AccountEntity getCrediting_account() {
        return crediting_account;
    }

    public void setCrediting_account(AccountEntity crediting_account) {
        this.crediting_account = crediting_account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        DateTimeZone ng = DateTimeZone.forID("Africa/Lagos");

        DateTime dt = new DateTime(ng);

        try {

            this.date = isoFormat.parse(dt.toString());

        } catch (ParseException e) {

            this.date = new Date();

        }
    }
}
