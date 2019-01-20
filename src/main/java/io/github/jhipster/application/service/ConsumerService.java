package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Consumer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Consumer.
 */
public interface ConsumerService {

    /**
     * Save a consumer.
     *
     * @param consumer the entity to save
     * @return the persisted entity
     */
    Consumer save(Consumer consumer);

    /**
     * Get all the consumers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Consumer> findAll(Pageable pageable);


    /**
     * Get the "id" consumer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Consumer> findOne(Long id);

    /**
     * Delete the "id" consumer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
