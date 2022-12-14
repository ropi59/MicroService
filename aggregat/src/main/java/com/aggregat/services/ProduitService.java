package com.aggregat.services;

import com.aggregat.DTO.ProduitDtoIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProduitService {

    private WebClient client;

    @Autowired
    ProduitService(@Qualifier ("webClientProduit") WebClient webClient){
        this.client = webClient;
    }

    /**
     * Récupère toutes les factures avec le détail des produits et le montant
     * @return une liste de factures détaillées
     */
    public Mono<List<ProduitDtoIn>> getAllProduits() {
        return client
                //Methode http
                .get()
                //Lance la requete (OBLIGATOIRE)
                .retrieve()
                //Récupération en flux
                .bodyToFlux(ProduitDtoIn.class)
                //Transforme le flux en mono sous forme de liste
                .collectList();
    }

    /**
     * Récupère la liste des produits par les ids des factures
     * @param ids l'id des factures, séparées par une virgule
     * @return les factures avec leurs produits et montants
     */
    public Mono<List<ProduitDtoIn>> getListProduitByIds(List<String> ids){
        //transforme ma liste en string en rajoutant une virgule après chaque id sauf à la fin
        String idsValues = String.join(",", ids);
        System.out.println(idsValues);
        return client
                .get()
                .uri("/ids?ids=" + idsValues)
                .retrieve()
                .bodyToFlux(ProduitDtoIn.class)
                .collectList();
    }

}
