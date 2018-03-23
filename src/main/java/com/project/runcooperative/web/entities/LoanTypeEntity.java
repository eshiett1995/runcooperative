package com.project.runcooperative.web.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.LocalDate;

import javax.persistence.*;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class LoanTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    private LocalDate dte;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "loanTypeEntity")
    //@JsonBackReference(value = "loanEntity" )
    private LoanEntity loanEntity;

    private String loanTypeName;

    private int interestRate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }


    public LoanEntity getLoanEntity() {
        return loanEntity;
    }

    public void setLoanEntity(LoanEntity loanEntity) {
        this.loanEntity = loanEntity;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public LocalDate getDte() {
        return dte;
    }

    public void setDte(LocalDate dte) {
        this.dte = dte;
    }
}
