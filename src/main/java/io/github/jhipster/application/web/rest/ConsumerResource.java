package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Consumer;
import io.github.jhipster.application.service.ConsumerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Consumer.
 */
@RestController
@RequestMapping("/api")
public class ConsumerResource {

    private final Logger log = LoggerFactory.getLogger(ConsumerResource.class);

    private static final String ENTITY_NAME = "consumer";

    private final ConsumerService consumerService;

    public ConsumerResource(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /**
     * POST  /consumers : Create a new consumer.
     *
     * @param consumer the consumer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consumer, or with status 400 (Bad Request) if the consumer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/consumers")
    @Timed
    public ResponseEntity<Consumer> createConsumer(@Valid @RequestBody Consumer consumer) throws URISyntaxException {
        log.debug("REST request to save Consumer : {}", consumer);
        if (consumer.getId() != null) {
            throw new BadRequestAlertException("A new consumer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Consumer result = consumerService.save(consumer);
        return ResponseEntity.created(new URI("/api/consumers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /consumers : Updates an existing consumer.
     *
     * @param consumer the consumer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consumer,
     * or with status 400 (Bad Request) if the consumer is not valid,
     * or with status 500 (Internal Server Error) if the consumer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/consumers")
    @Timed
    public ResponseEntity<Consumer> updateConsumer(@Valid @RequestBody Consumer consumer) throws URISyntaxException {
        log.debug("REST request to update Consumer : {}", consumer);
        if (consumer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Consumer result = consumerService.save(consumer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consumer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /consumers : get all the consumers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of consumers in body
     */
    @GetMapping("/consumers")
    @Timed
    public ResponseEntity<List<Consumer>> getAllConsumers(Pageable pageable) {
        log.debug("REST request to get a page of Consumers");
        Page<Consumer> page = consumerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/consumers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /consumers/:id : get the "id" consumer.
     *
     * @param id the id of the consumer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consumer, or with status 404 (Not Found)
     */
    @GetMapping("/consumers/{id}")
    @Timed
    public ResponseEntity<Consumer> getConsumer(@PathVariable Long id) {
        log.debug("REST request to get Consumer : {}", id);
        Optional<Consumer> consumer = consumerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consumer);
    }

    /**
     * DELETE  /consumers/:id : delete the "id" consumer.
     *
     * @param id the id of the consumer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/consumers/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsumer(@PathVariable Long id) {
        log.debug("REST request to delete Consumer : {}", id);
        consumerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
