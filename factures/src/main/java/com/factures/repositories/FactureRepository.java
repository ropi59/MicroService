package com.factures.repositories;

import com.factures.DAO.FactureDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FactureRepository extends MongoRepository <FactureDAO, String> {
}
