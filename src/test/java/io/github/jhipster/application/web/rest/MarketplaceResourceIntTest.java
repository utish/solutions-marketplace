package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.SolutionsMarketplaceApp;

import io.github.jhipster.application.domain.Marketplace;
import io.github.jhipster.application.repository.MarketplaceRepository;
import io.github.jhipster.application.service.MarketplaceService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MarketplaceResource REST controller.
 *
 * @see MarketplaceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SolutionsMarketplaceApp.class)
public class MarketplaceResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @Autowired
    private MarketplaceService marketplaceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMarketplaceMockMvc;

    private Marketplace marketplace;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MarketplaceResource marketplaceResource = new MarketplaceResource(marketplaceService);
        this.restMarketplaceMockMvc = MockMvcBuilders.standaloneSetup(marketplaceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Marketplace createEntity(EntityManager em) {
        Marketplace marketplace = new Marketplace()
            .name(DEFAULT_NAME);
        return marketplace;
    }

    @Before
    public void initTest() {
        marketplace = createEntity(em);
    }

    @Test
    @Transactional
    public void createMarketplace() throws Exception {
        int databaseSizeBeforeCreate = marketplaceRepository.findAll().size();

        // Create the Marketplace
        restMarketplaceMockMvc.perform(post("/api/marketplaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketplace)))
            .andExpect(status().isCreated());

        // Validate the Marketplace in the database
        List<Marketplace> marketplaceList = marketplaceRepository.findAll();
        assertThat(marketplaceList).hasSize(databaseSizeBeforeCreate + 1);
        Marketplace testMarketplace = marketplaceList.get(marketplaceList.size() - 1);
        assertThat(testMarketplace.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMarketplaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = marketplaceRepository.findAll().size();

        // Create the Marketplace with an existing ID
        marketplace.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarketplaceMockMvc.perform(post("/api/marketplaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketplace)))
            .andExpect(status().isBadRequest());

        // Validate the Marketplace in the database
        List<Marketplace> marketplaceList = marketplaceRepository.findAll();
        assertThat(marketplaceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = marketplaceRepository.findAll().size();
        // set the field null
        marketplace.setName(null);

        // Create the Marketplace, which fails.

        restMarketplaceMockMvc.perform(post("/api/marketplaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketplace)))
            .andExpect(status().isBadRequest());

        List<Marketplace> marketplaceList = marketplaceRepository.findAll();
        assertThat(marketplaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMarketplaces() throws Exception {
        // Initialize the database
        marketplaceRepository.saveAndFlush(marketplace);

        // Get all the marketplaceList
        restMarketplaceMockMvc.perform(get("/api/marketplaces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(marketplace.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMarketplace() throws Exception {
        // Initialize the database
        marketplaceRepository.saveAndFlush(marketplace);

        // Get the marketplace
        restMarketplaceMockMvc.perform(get("/api/marketplaces/{id}", marketplace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(marketplace.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMarketplace() throws Exception {
        // Get the marketplace
        restMarketplaceMockMvc.perform(get("/api/marketplaces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMarketplace() throws Exception {
        // Initialize the database
        marketplaceService.save(marketplace);

        int databaseSizeBeforeUpdate = marketplaceRepository.findAll().size();

        // Update the marketplace
        Marketplace updatedMarketplace = marketplaceRepository.findById(marketplace.getId()).get();
        // Disconnect from session so that the updates on updatedMarketplace are not directly saved in db
        em.detach(updatedMarketplace);
        updatedMarketplace
            .name(UPDATED_NAME);

        restMarketplaceMockMvc.perform(put("/api/marketplaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMarketplace)))
            .andExpect(status().isOk());

        // Validate the Marketplace in the database
        List<Marketplace> marketplaceList = marketplaceRepository.findAll();
        assertThat(marketplaceList).hasSize(databaseSizeBeforeUpdate);
        Marketplace testMarketplace = marketplaceList.get(marketplaceList.size() - 1);
        assertThat(testMarketplace.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMarketplace() throws Exception {
        int databaseSizeBeforeUpdate = marketplaceRepository.findAll().size();

        // Create the Marketplace

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMarketplaceMockMvc.perform(put("/api/marketplaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketplace)))
            .andExpect(status().isBadRequest());

        // Validate the Marketplace in the database
        List<Marketplace> marketplaceList = marketplaceRepository.findAll();
        assertThat(marketplaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMarketplace() throws Exception {
        // Initialize the database
        marketplaceService.save(marketplace);

        int databaseSizeBeforeDelete = marketplaceRepository.findAll().size();

        // Get the marketplace
        restMarketplaceMockMvc.perform(delete("/api/marketplaces/{id}", marketplace.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Marketplace> marketplaceList = marketplaceRepository.findAll();
        assertThat(marketplaceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Marketplace.class);
        Marketplace marketplace1 = new Marketplace();
        marketplace1.setId(1L);
        Marketplace marketplace2 = new Marketplace();
        marketplace2.setId(marketplace1.getId());
        assertThat(marketplace1).isEqualTo(marketplace2);
        marketplace2.setId(2L);
        assertThat(marketplace1).isNotEqualTo(marketplace2);
        marketplace1.setId(null);
        assertThat(marketplace1).isNotEqualTo(marketplace2);
    }
}
