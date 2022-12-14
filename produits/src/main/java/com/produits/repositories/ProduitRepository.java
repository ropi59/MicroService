package com.produits.repositories;

import com.produits.DAO.ProduitDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProduitRepository extends MongoRepository <ProduitDao, String> {
}
