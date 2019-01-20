package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.SolutionService;
import io.github.jhipster.application.domain.Solution;
import io.github.jhipster.application.repository.SolutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Solution.
 */
@Service
@Transactional
public class SolutionServiceImpl implements SolutionService {

    private final Logger log = LoggerFactory.getLogger(SolutionServiceImpl.class);

    private final SolutionRepository solutionRepository;

    public SolutionServiceImpl(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    /**
     * Save a solution.
     *
     * @param solution the entity to save
     * @return the persisted entity
     */
    @Override
    public Solution save(Solution solution) {
        log.debug("Request to save Solution : {}", solution);
        return solutionRepository.save(solution);
    }

    /**
     * Get all the solutions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Solution> findAll(Pageable pageable) {
        log.debug("Request to get all Solutions");
        return solutionRepository.findAll(pageable);
    }


    /**
     * Get one solution by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Solution> findOne(Long id) {
        log.debug("Request to get Solution : {}", id);
        return solutionRepository.findById(id);
    }

    /**
     * Delete the solution by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Solution : {}", id);
        solutionRepository.deleteById(id);
    }
}
