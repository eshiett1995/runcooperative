package com.project.runcooperative.web.entities;

import com.fasterxml.jackson.annotation.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class CustomerEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    private Date date_created;

    private String firstname;
    private String lastname;
    private String password;

    private String PhoneNumber;
    private String emailAddress;
    private String Salary;
    private int FixedRate;
    @OneToMany(cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    mappedBy = "Accountholder")
    @JsonBackReference(value = "Accounts")
    private List<AccountEntity> Accounts = new ArrayList<AccountEntity>();

    private String Address;

    private Long daysjoined;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "customerEntity")
    @JsonBackReference(value = "loanEntity")
    private LoanEntity loanEntity;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSalary() { return Salary; }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public List<AccountEntity> getAccounts() { return Accounts; }

    public void setAccounts(List<AccountEntity> accounts) { Accounts = accounts; }

    public int getFixedRate() {
        return FixedRate;
    }

    public void setFixedRate(int fixedRate) {
        FixedRate = fixedRate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword(){return password; }

    public void setPassword(String password) {this.password = password;}

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public LoanEntity getLoanEntity() {
        return loanEntity;
    }

    public void setLoanEntity(LoanEntity loanEntity) {
        this.loanEntity = loanEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDaysjoined() {
        return daysjoined;
    }

    public void setDaysjoined(Long daysjoined) {
        this.daysjoined = daysjoined;
    }


}
