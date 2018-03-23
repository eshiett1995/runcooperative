package com.project.runcooperative.web.services;


import com.project.runcooperative.web.services.defaultinterface.CookieServiceInt;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oto-obong on 18/10/2017.
 */

@Service
public class DefaultCookieService implements CookieServiceInt {




    @Override
    public boolean hasValidCookie(HttpServletRequest request) {


       return true;
    }

    @Override
    public Cookie generateCookie(String sessionId) {

        // generates cookie for gambeat.

        Cookie cookie = new Cookie("credentials", sessionId);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(6000);
        cookie.setDomain("localhost");
        cookie.setPath("/");

        return cookie;

    }


}
