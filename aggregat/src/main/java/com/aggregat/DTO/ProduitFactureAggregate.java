package com.aggregat.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitFactureAggregate {

    private FactureDtoOut idFacture;
    private List<ProduitDtoIn> listProduits;

    public double getMontant(){
        Double montant = 0d;
        for (ProduitDtoIn produit : this.listProduits){
            montant += produit.getPrix();
        }
        return montant;
    };
}
