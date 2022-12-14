package com.produits.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("produit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDao {

    @Id
    private String id;
    private String nom;
    private String description;
    private double prix;
}
