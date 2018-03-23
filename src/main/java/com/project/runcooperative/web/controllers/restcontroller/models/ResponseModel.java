package com.project.runcooperative.web.controllers.restcontroller.models;

import org.springframework.stereotype.Component;

/**
 * Created by Oto-obong on 19/03/2018.
 */

@Component
public class ResponseModel {


    String responseMessage;

    boolean isSuccessful;

    String fullname;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {

        this.responseMessage = responseMessage;
    }

    public String getFullname() {

        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
