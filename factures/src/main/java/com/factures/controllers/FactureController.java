package com.factures.controllers;

import com.factures.DAO.FactureDAO;
import com.factures.services.FactureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("factures")
@CrossOrigin
public class FactureController {
    private FactureService service;

    public FactureController(FactureService service) {
        this.service = service;
    }

    /**
     * Récupère toutes les factures
     *
     * @return une liste de toutes les factures
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<FactureDAO> findAll() {
        return service.findAll();
    }

    /**
     * Enregistre une facture
     *
     * @param entity la facture à enregistrer
     * @return la facture sauvegardée
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public FactureDAO save(@RequestBody FactureDAO entity) {
        return service.save(entity);
    }

    /**
     * Mets à jour une facture
     *
     * @param facture la facture à modifier
     * @return la facture modifiée
     */
    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public FactureDAO update(@RequestBody FactureDAO facture, @PathVariable String id) {
        if ( !id.equals(facture.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id non trouvé.");
        }
        return service.update(facture);
    }

    /**
     * Recherche une facture par son id
     *
     * @param id l'id de la facture à rechercher
     * @return la facture trouvée si elle existe
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public FactureDAO findById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Supprime une facture par son id
     *
     * @param id l'id de la facture à supprimer
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }
}
