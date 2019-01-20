package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Marketplace;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Marketplace.
 */
public interface MarketplaceService {

    /**
     * Save a marketplace.
     *
     * @param marketplace the entity to save
     * @return the persisted entity
     */
    Marketplace save(Marketplace marketplace);

    /**
     * Get all the marketplaces.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Marketplace> findAll(Pageable pageable);


    /**
     * Get the "id" marketplace.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Marketplace> findOne(Long id);

    /**
     * Delete the "id" marketplace.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
