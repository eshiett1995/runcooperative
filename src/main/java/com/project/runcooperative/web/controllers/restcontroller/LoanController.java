package com.project.runcooperative.web.controllers.restcontroller;


import com.project.runcooperative.web.controllers.restcontroller.models.Loans;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    @RequestMapping(value = "/loan/regular/add", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void PopulatePriviledge(@RequestBody Loans loans) {

        loans.getFirstname();
        loans.getLastname();




    }
}
