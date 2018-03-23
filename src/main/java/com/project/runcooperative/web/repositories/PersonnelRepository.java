package com.project.runcooperative.web.repositories;

import com.project.runcooperative.web.entities.PersonnelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends CrudRepository<PersonnelEntity,Long> {


    //PersonnelEntity findPersonnelEntityByEmailAddressAndPassword(String EmailAddress, String Password);
}
