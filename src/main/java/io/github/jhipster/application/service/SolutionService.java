package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Solution;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Solution.
 */
public interface SolutionService {

    /**
     * Save a solution.
     *
     * @param solution the entity to save
     * @return the persisted entity
     */
    Solution save(Solution solution);

    /**
     * Get all the solutions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Solution> findAll(Pageable pageable);


    /**
     * Get the "id" solution.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Solution> findOne(Long id);

    /**
     * Delete the "id" solution.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
