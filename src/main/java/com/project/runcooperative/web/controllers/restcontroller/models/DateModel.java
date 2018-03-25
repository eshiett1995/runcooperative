package com.project.runcooperative.web.controllers.restcontroller.models;

import org.springframework.stereotype.Component;

/**
 * Created by Oto-obong on 25/03/2018.
 */

@Component
public class DateModel {

    private int month;

    private int year;

    public int getMonth() {

        return month;

    }

    public void setMonth(int month) {

        this.month = month;

    }

    public int getYear() {

        return year;

    }

    public void setYear(int year) {

        this.year = year;

    }
}
