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
 * A Consumer.
 */
@Entity
@Table(name = "consumer")
public class Consumer implements Serializable {

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
    @JsonIgnoreProperties("consumers")
    private Marketplace marketplace;

    @OneToMany(mappedBy = "consumer")
    private Set<Problem> problems = new HashSet<>();
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

    public Consumer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Consumer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public Consumer marketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
        return this;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public Consumer problems(Set<Problem> problems) {
        this.problems = problems;
        return this;
    }

    public Consumer addProblem(Problem problem) {
        this.problems.add(problem);
        problem.setConsumer(this);
        return this;
    }

    public Consumer removeProblem(Problem problem) {
        this.problems.remove(problem);
        problem.setConsumer(null);
        return this;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
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
        Consumer consumer = (Consumer) o;
        if (consumer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consumer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Consumer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
