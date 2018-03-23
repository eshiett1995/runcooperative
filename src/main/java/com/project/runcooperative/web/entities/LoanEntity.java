package com.project.runcooperative.web.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    private Date date;

    @OneToOne(targetEntity = LoanTypeEntity.class)
    @JoinColumn(name = "loan_id", nullable = false)
    //@JsonManagedReference(value = "loanTypeEntity")
    private LoanTypeEntity loanTypeEntity;


    @OneToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id", nullable = false)
    //@JsonManagedReference(value = "customerEntity")
    private CustomerEntity customerEntity;

    private String loanStatus; //pending, accepted, declined, paid, accepted-ongoing

    private double amount;

    private double payback_amount;

    private boolean hasbeen_paid;

    private int duration;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LoanTypeEntity getLoanTypeEntity() {
        return loanTypeEntity;
    }

    public void setLoanTypeEntity(LoanTypeEntity loanTypeEntity) {
        this.loanTypeEntity = loanTypeEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPayback_amount() {
        return payback_amount;
    }

    public void setPayback_amount(double payback_amount) {
        this.payback_amount = payback_amount;
    }

    public boolean isHasbeen_paid() {
        return hasbeen_paid;
    }

    public void setHasbeen_paid(boolean hasbeen_paid) {
        this.hasbeen_paid = hasbeen_paid;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public LoanEntity setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
        return this;
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
