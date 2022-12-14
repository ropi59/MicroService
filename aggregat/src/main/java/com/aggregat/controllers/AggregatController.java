package com.aggregat.controllers;

import com.aggregat.DTO.FactureDtoIn;
import com.aggregat.DTO.ProduitFactureAggregate;
import com.aggregat.services.AggregatService;
import com.aggregat.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("factures")
@CrossOrigin
public class AggregatController {

    @Autowired
    private AggregatService aggregatService;

    @Autowired
    private FactureService factureService;

    /**
     * Récupère toutes les factures détaillées
     * @return une liste de factures détaillées
     */
    @GetMapping("/produits")
    public Mono<List<ProduitFactureAggregate>> getAllFacturesWithProduits() {
        return aggregatService.getAllFacturesWithProduits();
    }

    /**
     * Récupère une facture par son id
     * @param id l'id de la facture à rechercher
     * @return une facture avec tous ses détails
     */
    @GetMapping("/{id}/produits")
    public Mono<ProduitFactureAggregate> getFactureWithProduits(@PathVariable String id){
        return aggregatService.getFactureWithProduits(id);
    }
}
