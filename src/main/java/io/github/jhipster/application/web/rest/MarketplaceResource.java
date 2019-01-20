package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Marketplace;
import io.github.jhipster.application.service.MarketplaceService;
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
 * REST controller for managing Marketplace.
 */
@RestController
@RequestMapping("/api")
public class MarketplaceResource {

    private final Logger log = LoggerFactory.getLogger(MarketplaceResource.class);

    private static final String ENTITY_NAME = "marketplace";

    private final MarketplaceService marketplaceService;

    public MarketplaceResource(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

    /**
     * POST  /marketplaces : Create a new marketplace.
     *
     * @param marketplace the marketplace to create
     * @return the ResponseEntity with status 201 (Created) and with body the new marketplace, or with status 400 (Bad Request) if the marketplace has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/marketplaces")
    @Timed
    public ResponseEntity<Marketplace> createMarketplace(@Valid @RequestBody Marketplace marketplace) throws URISyntaxException {
        log.debug("REST request to save Marketplace : {}", marketplace);
        if (marketplace.getId() != null) {
            throw new BadRequestAlertException("A new marketplace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Marketplace result = marketplaceService.save(marketplace);
        return ResponseEntity.created(new URI("/api/marketplaces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /marketplaces : Updates an existing marketplace.
     *
     * @param marketplace the marketplace to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated marketplace,
     * or with status 400 (Bad Request) if the marketplace is not valid,
     * or with status 500 (Internal Server Error) if the marketplace couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/marketplaces")
    @Timed
    public ResponseEntity<Marketplace> updateMarketplace(@Valid @RequestBody Marketplace marketplace) throws URISyntaxException {
        log.debug("REST request to update Marketplace : {}", marketplace);
        if (marketplace.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Marketplace result = marketplaceService.save(marketplace);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, marketplace.getId().toString()))
            .body(result);
    }

    /**
     * GET  /marketplaces : get all the marketplaces.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of marketplaces in body
     */
    @GetMapping("/marketplaces")
    @Timed
    public ResponseEntity<List<Marketplace>> getAllMarketplaces(Pageable pageable) {
        log.debug("REST request to get a page of Marketplaces");
        Page<Marketplace> page = marketplaceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/marketplaces");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /marketplaces/:id : get the "id" marketplace.
     *
     * @param id the id of the marketplace to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the marketplace, or with status 404 (Not Found)
     */
    @GetMapping("/marketplaces/{id}")
    @Timed
    public ResponseEntity<Marketplace> getMarketplace(@PathVariable Long id) {
        log.debug("REST request to get Marketplace : {}", id);
        Optional<Marketplace> marketplace = marketplaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(marketplace);
    }

    /**
     * DELETE  /marketplaces/:id : delete the "id" marketplace.
     *
     * @param id the id of the marketplace to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/marketplaces/{id}")
    @Timed
    public ResponseEntity<Void> deleteMarketplace(@PathVariable Long id) {
        log.debug("REST request to delete Marketplace : {}", id);
        marketplaceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
