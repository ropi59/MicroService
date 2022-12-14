package com.aggregat.services;

import com.aggregat.DTO.FactureDtoIn;
import com.aggregat.DTO.FactureDtoOut;
import com.aggregat.DTO.ProduitDtoIn;
import com.aggregat.DTO.ProduitFactureAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatService {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private FactureService factureService;

    /**
     * Récupère toutes les factures avec le détail des produits
     * @return une liste de factures avec les détails de tous les produits et le montant total
     */
    public Mono<List<ProduitFactureAggregate>> getAllFacturesWithProduits(){
        //importe les methodes de service.
        Mono<List<ProduitDtoIn>> listProduits = produitService.getAllProduits();
        Mono<List<FactureDtoIn>> listFactures = factureService.getAllFactures();
        return Mono.zip(listProduits, listFactures).map(tuple -> fusion( tuple.getT1(), tuple.getT2()));
    }

    /**
     * Rajoute le détail de chaque produit présent dans les factures
     * @param produits la liste des produits
     * @param factures la liste des factures
     * @return une liste détaillée de toutes les factures avec le détail des produits
     */
    private List<ProduitFactureAggregate> fusion(List<ProduitDtoIn> produits, List<FactureDtoIn> factures){
        List<ProduitFactureAggregate> results = new ArrayList<>();
        for (FactureDtoIn facture : factures){
            List<ProduitDtoIn> item = new ArrayList<>();
            for (ProduitDtoIn produit : produits){
                if (facture.getListProduits().contains(produit.getId())){
                    item.add(produit);
                }
            }
            results.add(new ProduitFactureAggregate(new FactureDtoOut(facture.getId()), item));
        }
        return results;
    }

    /**
     * Récupère une facture par son id avec l'id de tous les produits contenus
     * @param id l'id de la facture
     * @return une facture avec les ids de ses produits
     */
    public Mono<ProduitFactureAggregate> getFactureWithProduits(String id){
        Mono<FactureDtoIn> facture = factureService.getFactureById(id);
        Mono<List<ProduitDtoIn>> produit = facture.flatMap(item -> {
           return produitService.getListProduitByIds(item.getListProduits());
        });
        return facture.zipWith(produit).map(t -> new ProduitFactureAggregate(new FactureDtoOut(t.getT1().getId()), t.getT2()));
    }

}
