package com.project.runcooperative.web.repositories;

import com.project.runcooperative.web.entities.LoanTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanTypeRepository extends CrudRepository<LoanTypeEntity,Long> {
}
