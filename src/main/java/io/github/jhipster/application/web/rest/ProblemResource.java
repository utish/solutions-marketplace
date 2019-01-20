package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Problem;
import io.github.jhipster.application.service.ProblemService;
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
 * REST controller for managing Problem.
 */
@RestController
@RequestMapping("/api")
public class ProblemResource {

    private final Logger log = LoggerFactory.getLogger(ProblemResource.class);

    private static final String ENTITY_NAME = "problem";

    private final ProblemService problemService;

    public ProblemResource(ProblemService problemService) {
        this.problemService = problemService;
    }

    /**
     * POST  /problems : Create a new problem.
     *
     * @param problem the problem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new problem, or with status 400 (Bad Request) if the problem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/problems")
    @Timed
    public ResponseEntity<Problem> createProblem(@Valid @RequestBody Problem problem) throws URISyntaxException {
        log.debug("REST request to save Problem : {}", problem);
        if (problem.getId() != null) {
            throw new BadRequestAlertException("A new problem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Problem result = problemService.save(problem);
        return ResponseEntity.created(new URI("/api/problems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /problems : Updates an existing problem.
     *
     * @param problem the problem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated problem,
     * or with status 400 (Bad Request) if the problem is not valid,
     * or with status 500 (Internal Server Error) if the problem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/problems")
    @Timed
    public ResponseEntity<Problem> updateProblem(@Valid @RequestBody Problem problem) throws URISyntaxException {
        log.debug("REST request to update Problem : {}", problem);
        if (problem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Problem result = problemService.save(problem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, problem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /problems : get all the problems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of problems in body
     */
    @GetMapping("/problems")
    @Timed
    public ResponseEntity<List<Problem>> getAllProblems(Pageable pageable) {
        log.debug("REST request to get a page of Problems");
        Page<Problem> page = problemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/problems");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /problems/:id : get the "id" problem.
     *
     * @param id the id of the problem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the problem, or with status 404 (Not Found)
     */
    @GetMapping("/problems/{id}")
    @Timed
    public ResponseEntity<Problem> getProblem(@PathVariable Long id) {
        log.debug("REST request to get Problem : {}", id);
        Optional<Problem> problem = problemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(problem);
    }

    /**
     * DELETE  /problems/:id : delete the "id" problem.
     *
     * @param id the id of the problem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/problems/{id}")
    @Timed
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        log.debug("REST request to delete Problem : {}", id);
        problemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
