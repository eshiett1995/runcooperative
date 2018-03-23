package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.Priviledge;
import com.project.runcooperative.web.repositories.PriviledgeRepository;
import com.project.runcooperative.web.services.defaultinterface.PriviledgeServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PriviledgeService implements PriviledgeServiceInt {

    @Autowired
    PriviledgeRepository priviledgeRepository;


    @Override
    public void save(Priviledge priviledge) {

        priviledgeRepository.save(priviledge);
    }

    @Override
    public List<Priviledge> getAll() {

       return (List<Priviledge>) priviledgeRepository.findAll();

    }

    @Override
    public Priviledge getPriviledge(long id) {

            return priviledgeRepository.getPriviledgeById(id);


    }

    @Override
    public Boolean AuthenticatePersonnel(String email, String Password) {

        //priviledgeRepository.
        return true;
    }
}
