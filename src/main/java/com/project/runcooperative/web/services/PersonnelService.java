package com.project.runcooperative.web.services;

import com.project.runcooperative.web.entities.PersonnelEntity;
import com.project.runcooperative.web.repositories.PersonnelRepository;
import com.project.runcooperative.web.services.defaultinterface.PersonnelServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService implements PersonnelServiceInt{

    @Autowired
    PersonnelRepository personnelRepository;

    @Override
    public void save(PersonnelEntity personel) {

        personnelRepository.save(personel);
    }

    @Override
    public void delete() {

    }

    @Override
    public Boolean AuthenticatePersonnel(String email, String Password) {

       List<PersonnelEntity> personnelEntities = (List<PersonnelEntity>) personnelRepository.findAll();

        for (PersonnelEntity p: personnelEntities) {

            if(p.getEmailAddress().equals(email) && p.getPassword().equals(Password)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public PersonnelEntity GetPersonnelById(Long id) {
        return personnelRepository.findOne(id);
    }

    @Override
    public List<PersonnelEntity> GetAll() {
        return (List<PersonnelEntity>) personnelRepository.findAll();
    }
}

