package com.project.runcooperative.web.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PersonnelEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    private Date date_created;
    private String firstname;
    private String lastname;

    @OneToOne(targetEntity = Priviledge.class)
    private Priviledge priviledge;
    private String PhoneNumber;

    @Column(name = "email")
    private String EmailAddress;
    private String Username;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
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

    public Priviledge getPriviledge() {
        return priviledge;
    }

    public void setPriviledge(Priviledge priviledge) {
        this.priviledge = priviledge;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
}
