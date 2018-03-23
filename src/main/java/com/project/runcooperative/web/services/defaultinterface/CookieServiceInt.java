package com.project.runcooperative.web.services.defaultinterface;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oto-obong on 18/10/2017.
 */


public interface CookieServiceInt {

    boolean hasValidCookie(HttpServletRequest request);

    Cookie generateCookie(String sessionID);

    //User getUser(String cookieName, HttpServletRequest request);
}
