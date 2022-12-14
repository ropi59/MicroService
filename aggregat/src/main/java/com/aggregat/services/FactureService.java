package com.aggregat.services;

import com.aggregat.DTO.FactureDtoIn;
import com.aggregat.DTO.ProduitFactureAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FactureService {

    private WebClient client;

    @Autowired
    FactureService(@Qualifier ("webClientFacture") WebClient webClient){
        this.client = webClient;
    }

    /**
     * Récupère toutes les factures
     * @return une liste de factures
     */
    public Mono<List<FactureDtoIn>> getAllFactures(){
        return client
                .get()
                .retrieve()
                .bodyToFlux(FactureDtoIn.class)
                .collectList();
    }

    /**
     * Récupère une facture par son id
     * @param id l'id de la facture à chercher
     * @return la facture trouvée
     */
    public Mono<FactureDtoIn> getFactureById(String id){
        return client
                .get()
                .uri("/" +id)
                .retrieve()
                .bodyToMono(FactureDtoIn.class);
    }
}
