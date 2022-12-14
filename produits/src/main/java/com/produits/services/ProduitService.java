package com.produits.services;

import com.produits.DAO.ProduitDao;
import com.produits.repositories.ProduitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProduitService {

    private Logger logger = LoggerFactory.getLogger(ProduitDao.class);
    private ProduitRepository repository;

    public ProduitService(ProduitRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère tous les produits
     * @return une liste de tous les produits
     */
    public List<ProduitDao> findAll() {
        return repository.findAll();
    }

    /**
     * Enregistre un produit
     * @param entity le produit à enregistrer
     * @return le produit sauvegardé
     */
    public ProduitDao save(ProduitDao entity) {
        return repository.save(entity);
    }

    /**
     * Mets à jour un produit
     * @param produit le produit à modifier
     * @return le produit modifié
     */
    public ProduitDao update(ProduitDao produit) {
        if (!this.repository.existsById(produit.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "produit non trouvé.");
        }
        return this.repository.save(produit);
    }

    /**
     * Recherche un produit par son id
     * @param id l'id du produit à rechercher
     * @return le produit trouvé si elle existe
     */
    public ProduitDao findById(String id) {
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalid: " + id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprime un produit par son id
     * @param id l'id du produit à supprimer
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     * Enregistre une liste de produits
     * @param entities la liste de produits à enregistrer
     * @return la liste des produits sauvegardés
     */
    public List<ProduitDao> saveAll(Iterable<ProduitDao> entities) {
        return repository.saveAll(entities);
    }

    /**
     * Récupère une liste de produitDao à partir d'une liste d'id
     * @param ids la liste des ids à chercher
     * @return une liste de produits.
     */
    public List<ProduitDao> findAllById(List<String> ids) {
        return repository.findAllById(ids);
    }
}
