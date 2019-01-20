package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Producer.
 */
@Entity
@Table(name = "producer")
public class Producer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JsonIgnoreProperties("producers")
    private Marketplace marketplace;

    @OneToMany(mappedBy = "producer")
    private Set<Solution> solutions = new HashSet<>();
    @OneToMany(mappedBy = "producer")
    private Set<Recipe> recipes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Producer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Producer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public Producer marketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
        return this;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }

    public Producer solutions(Set<Solution> solutions) {
        this.solutions = solutions;
        return this;
    }

    public Producer addSolution(Solution solution) {
        this.solutions.add(solution);
        solution.setProducer(this);
        return this;
    }

    public Producer removeSolution(Solution solution) {
        this.solutions.remove(solution);
        solution.setProducer(null);
        return this;
    }

    public void setSolutions(Set<Solution> solutions) {
        this.solutions = solutions;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public Producer recipes(Set<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }

    public Producer addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
        recipe.setProducer(this);
        return this;
    }

    public Producer removeRecipe(Recipe recipe) {
        this.recipes.remove(recipe);
        recipe.setProducer(null);
        return this;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Producer producer = (Producer) o;
        if (producer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), producer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Producer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
