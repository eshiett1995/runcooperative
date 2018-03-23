package com.project.runcooperative.web.services.defaultinterface;

import com.project.runcooperative.web.entities.Priviledge;

import java.util.List;

public interface PriviledgeServiceInt {

    public void save(Priviledge priviledge );

    public List<Priviledge> getAll();

    public Priviledge getPriviledge(long id);

    public Boolean AuthenticatePersonnel(String email,String Password);


}
