package com.project.runcooperative.web.repositories;

import com.project.runcooperative.web.entities.CustomerEntity;
import com.project.runcooperative.web.entities.LoanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<LoanEntity,Long> {

   List<LoanEntity> getAllByCustomerEntityOrderByIdDesc(CustomerEntity customerEntity);

   LoanEntity findById(long id);
}
