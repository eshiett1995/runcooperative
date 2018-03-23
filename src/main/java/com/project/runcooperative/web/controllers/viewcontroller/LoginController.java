package com.project.runcooperative.web.controllers.viewcontroller;

import com.project.runcooperative.web.entities.AccountEntity;
import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.Enum;
import com.project.runcooperative.web.entities.PersonnelEntity;
import com.project.runcooperative.web.repositories.CustomerRepository;
import com.project.runcooperative.web.services.DefaultCookieService;
import com.project.runcooperative.web.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
import java.util.Collections;
import java.util.List;

@Controller
public class LoginController {


    @Autowired
    DefaultCookieService defaultCookieService;

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ShowLoginPage(Model model){

        model.addAttribute("personnel", new PersonnelEntity());

        return "login";
    }

    @RequestMapping(value = "/login/authenticate", method = RequestMethod.POST)
    public String AuthenticateLogin(@ModelAttribute("personnel") PersonnelEntity personnelEntity, Model model, BindingResult result, HttpServletRequest req, HttpServletResponse response){

        if(result.hasErrors()){
            System.out.println(result.getAllErrors().toString());
        }

        if(personnelService.AuthenticatePersonnel(personnelEntity.getEmailAddress(),personnelEntity.getPassword())){

            HttpSession session = req.getSession();

            session.setAttribute(session.getId(),personnelEntity.getEmailAddress());

            System.out.print("login page id " + session.getId());

            response.addCookie(defaultCookieService.generateCookie(session.getId()));

            return "home";

        }else{

            model.addAttribute("personnel", new PersonnelEntity());

            return "login";

        }


    }

}
