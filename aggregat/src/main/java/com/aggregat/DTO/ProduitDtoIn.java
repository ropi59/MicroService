package com.aggregat.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDtoIn {

    private String id;
    private String nom;
    private String description;
    private double prix;
}
