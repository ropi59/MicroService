package com.produits.controllers;

import com.produits.DAO.ProduitDao;
import com.produits.services.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("produits")
@CrossOrigin
public class ProduitController {

    private ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    /**
     * Récupère tous les produits
     *
     * @return une liste de tous les produits
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ProduitDao> findAll() {
        return service.findAll();
    }

    /**
     * Enregistre un produit
     *
     * @param entity le produit à enregistrer
     * @return le produit sauvegardé
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProduitDao save(@RequestBody ProduitDao entity) {
        return service.save(entity);
    }

    /**
     * Mets à jour un produit
     *
     * @param produit le produit à modifier
     * @return le produit modifié
     */
    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ProduitDao update(@RequestBody ProduitDao produit, @PathVariable String id) {
        if ( !id.equals(produit.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id non trouvé.");
        }
        return service.update(produit);
    }

    /**
     * Recherche un produit par son id
     *
     * @param id l'id du produit à rechercher
     * @return le produit trouvé si elle existe
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public ProduitDao findById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Supprime un produit par son id
     *
     * @param id l'id du produit à supprimer
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    /**
     * Enregistre une liste de produits
     *
     * @param entities la liste de produits à enregistrer
     * @return la liste des produits sauvegardés
     */
    @PostMapping("all")
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<ProduitDao> saveAll(@RequestBody Iterable<ProduitDao> entities) {
        return service.saveAll(entities);
    }

    /**
     * Récupère une liste de produitDao à partir d'une liste d'id
     *
     * @param ids la liste des ids à chercher
     * @return une liste de produits.
     */
    @GetMapping("ids")
    public List<ProduitDao> findAllById(@RequestParam String ids) {
        List<String> listId = new ArrayList<>(Arrays.asList(ids.split(",")));
        return service.findAllById(listId);
    }
}
