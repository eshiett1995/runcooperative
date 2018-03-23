package com.project.runcooperative.web.repositories;

import com.project.runcooperative.web.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity,Long> {

}
