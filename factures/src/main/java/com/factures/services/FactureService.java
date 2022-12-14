package com.factures.services;

import com.factures.DAO.FactureDAO;
import com.factures.repositories.FactureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FactureService {
    private Logger logger = LoggerFactory.getLogger(FactureDAO.class);
    private FactureRepository repository;

    public FactureService(FactureRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère toutes les factures
     * @return une liste de toutes les factures
     */
    public List<FactureDAO> findAll() {
        return repository.findAll();
    }

    /**
     * Enregistre une facture
     * @param entity la facture à enregistrer
     * @return la facture sauvegardée
     */
    public FactureDAO save(FactureDAO entity) {
        return repository.save(entity);
    }

    /**
     * Mets à jour une facture
     * @param facture la facture à modifier
     * @return la facture modifiée
     */
    public FactureDAO update(FactureDAO facture) {
        if (!this.repository.existsById(facture.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "facture non trouvée.");
        }
        return this.repository.save(facture);
    }

    /**
     * Recherche une facture par son id
     * @param id l'id de la facture à rechercher
     * @return la facture trouvée si elle existe
     */
    public FactureDAO findById(String id) {
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalid: " + id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprime une facture par son id
     * @param id l'id de la facture à supprimer
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
