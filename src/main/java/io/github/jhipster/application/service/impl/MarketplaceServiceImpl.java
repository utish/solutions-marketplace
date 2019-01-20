package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MarketplaceService;
import io.github.jhipster.application.domain.Marketplace;
import io.github.jhipster.application.repository.MarketplaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Marketplace.
 */
@Service
@Transactional
public class MarketplaceServiceImpl implements MarketplaceService {

    private final Logger log = LoggerFactory.getLogger(MarketplaceServiceImpl.class);

    private final MarketplaceRepository marketplaceRepository;

    public MarketplaceServiceImpl(MarketplaceRepository marketplaceRepository) {
        this.marketplaceRepository = marketplaceRepository;
    }

    /**
     * Save a marketplace.
     *
     * @param marketplace the entity to save
     * @return the persisted entity
     */
    @Override
    public Marketplace save(Marketplace marketplace) {
        log.debug("Request to save Marketplace : {}", marketplace);
        return marketplaceRepository.save(marketplace);
    }

    /**
     * Get all the marketplaces.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Marketplace> findAll(Pageable pageable) {
        log.debug("Request to get all Marketplaces");
        return marketplaceRepository.findAll(pageable);
    }


    /**
     * Get one marketplace by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Marketplace> findOne(Long id) {
        log.debug("Request to get Marketplace : {}", id);
        return marketplaceRepository.findById(id);
    }

    /**
     * Delete the marketplace by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Marketplace : {}", id);
        marketplaceRepository.deleteById(id);
    }
}
