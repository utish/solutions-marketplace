package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ConsumerService;
import io.github.jhipster.application.domain.Consumer;
import io.github.jhipster.application.repository.ConsumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Consumer.
 */
@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

    private final Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    private final ConsumerRepository consumerRepository;

    public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    /**
     * Save a consumer.
     *
     * @param consumer the entity to save
     * @return the persisted entity
     */
    @Override
    public Consumer save(Consumer consumer) {
        log.debug("Request to save Consumer : {}", consumer);
        return consumerRepository.save(consumer);
    }

    /**
     * Get all the consumers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Consumer> findAll(Pageable pageable) {
        log.debug("Request to get all Consumers");
        return consumerRepository.findAll(pageable);
    }


    /**
     * Get one consumer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Consumer> findOne(Long id) {
        log.debug("Request to get Consumer : {}", id);
        return consumerRepository.findById(id);
    }

    /**
     * Delete the consumer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Consumer : {}", id);
        consumerRepository.deleteById(id);
    }
}
