package com.project.runcooperative.web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    //@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate date_created;

    private String AccountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id")

    @JsonManagedReference
    private CustomerEntity Accountholder;

    private double Amount;

    public CustomerEntity getAccountholder() {
        return Accountholder;
    }

    public void setAccountholder(CustomerEntity accountholder) {
        Accountholder = accountholder;
    }

    public String getAccountNumber() {
        return AccountNumber;

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

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }
}
