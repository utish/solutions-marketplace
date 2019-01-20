package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.RecipeService;
import io.github.jhipster.application.domain.Recipe;
import io.github.jhipster.application.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Recipe.
 */
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final Logger log = LoggerFactory.getLogger(RecipeServiceImpl.class);

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Save a recipe.
     *
     * @param recipe the entity to save
     * @return the persisted entity
     */
    @Override
    public Recipe save(Recipe recipe) {
        log.debug("Request to save Recipe : {}", recipe);
        return recipeRepository.save(recipe);
    }

    /**
     * Get all the recipes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Recipe> findAll() {
        log.debug("Request to get all Recipes");
        return recipeRepository.findAll();
    }


    /**
     * Get one recipe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Recipe> findOne(Long id) {
        log.debug("Request to get Recipe : {}", id);
        return recipeRepository.findById(id);
    }

    /**
     * Delete the recipe by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recipe : {}", id);
        recipeRepository.deleteById(id);
    }
}
