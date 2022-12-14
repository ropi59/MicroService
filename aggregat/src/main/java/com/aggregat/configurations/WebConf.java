package com.aggregat.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConf {

    /**
     * WebClient pour les requetes sur produits
     */
    @Value("${environment.domaineProduit}")
    private String produitUrl;

    @Bean
    public WebClient webClientProduit () {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(produitUrl)
                .build();
    }

    /**
     * WebClient pour les requetes sur factures
     */
    @Value("${environment.domaineFacture}")
    private String factureUrl;

    @Bean
    public WebClient webClientFacture () {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(factureUrl)
                .build();
    }
}
