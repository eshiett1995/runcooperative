package com.project.runcooperative.web.services.defaultinterface;

import com.project.runcooperative.web.entities.PersonnelEntity;

import java.util.List;

public interface PersonnelServiceInt {
    void save(PersonnelEntity personnel);
    void delete();
    Boolean AuthenticatePersonnel(String email,String Password);
    PersonnelEntity GetPersonnelById(Long id);
    List<PersonnelEntity> GetAll();

}
