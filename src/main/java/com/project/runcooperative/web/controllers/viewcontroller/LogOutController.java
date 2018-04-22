package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.entities.PersonnelEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Oto-obong on 11/02/2018.
 */

@Controller
public class LogOutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String SignOut(HttpServletResponse response){

            Cookie cookie = new Cookie("credentials",null);

            cookie.setSecure(false);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(6000);
            cookie.setDomain("run-cooperative.herokuapp.com");
            cookie.setPath("/");

            response.addCookie(cookie);

            return "redirect:/";


    }
}
