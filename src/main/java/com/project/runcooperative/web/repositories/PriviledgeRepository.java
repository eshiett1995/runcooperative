package com.project.runcooperative.web.repositories;

import com.project.runcooperative.web.entities.Priviledge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriviledgeRepository extends CrudRepository<Priviledge, Long> {

    Priviledge getPriviledgeById(long id);

}
