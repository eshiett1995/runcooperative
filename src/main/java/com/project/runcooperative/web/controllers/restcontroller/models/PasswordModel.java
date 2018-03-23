package com.project.runcooperative.web.controllers.restcontroller.models;

/**
 * Created by Oto-obong on 19/03/2018.
 */
public class PasswordModel {

    private String Email;

    private String OldPassword;

    private String NewPassword;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }
}
