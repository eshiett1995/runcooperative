package com.project.runcooperative.web.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.LocalDate;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class TransactionEntity{

    public enum TransactionType { Purchases, Sales}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    private LocalDate date_created;

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


    public LocalDate getDate_created() {
        return date_created;
    }


    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
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
}
