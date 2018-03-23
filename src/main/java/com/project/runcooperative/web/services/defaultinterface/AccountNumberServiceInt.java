package com.project.runcooperative.web.services.defaultinterface;

public interface AccountNumberServiceInt {

    public String generateToken(int length);

    public Boolean generateUniqueToken(String token);

    public String getAccountNumber();
}
