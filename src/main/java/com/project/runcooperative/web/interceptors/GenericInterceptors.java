package com.project.runcooperative.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Oto-obong on 31/01/2018.
 */

@Component
public class GenericInterceptors extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url = httpServletRequest.getRequestURI();

        System.out.print(url);

        Cookie[] cookieArray = httpServletRequest.getCookies();

        boolean hasValidCookie = false;

        if(cookieArray != null) {   //checks if the cookie array is null


            for (Cookie cookie : cookieArray) { // checks if gambeat cookie is amongst the cookies

                if(cookie.getName().equalsIgnoreCase("credentials")){

                    HttpSession session = httpServletRequest.getSession(true);

                    String name = (String) session.getAttribute(cookie.getValue());

                    if(name != null){

                        return true;

                    }else{

                        httpServletResponse.sendRedirect("/");

                        return false;

                    }


                }
            }

        }else{

            httpServletResponse.sendRedirect("/");

            return false;
        }

        httpServletResponse.sendRedirect("/");

        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
