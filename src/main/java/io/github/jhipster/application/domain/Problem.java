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
 * A Problem.
 */
@Entity
@Table(name = "problem")
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "statement", nullable = false)
    private String statement;

    @ManyToOne
    @JsonIgnoreProperties("problems")
    private Consumer consumer;

    @OneToMany(mappedBy = "problem")
    private Set<Solution> solutions = new HashSet<>();
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

    public Problem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatement() {
        return statement;
    }

    public Problem statement(String statement) {
        this.statement = statement;
        return this;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Problem consumer(Consumer consumer) {
        this.consumer = consumer;
        return this;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }

    public Problem solutions(Set<Solution> solutions) {
        this.solutions = solutions;
        return this;
    }

    public Problem addSolution(Solution solution) {
        this.solutions.add(solution);
        solution.setProblem(this);
        return this;
    }

    public Problem removeSolution(Solution solution) {
        this.solutions.remove(solution);
        solution.setProblem(null);
        return this;
    }

    public void setSolutions(Set<Solution> solutions) {
        this.solutions = solutions;
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
        Problem problem = (Problem) o;
        if (problem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), problem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Problem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", statement='" + getStatement() + "'" +
            "}";
    }
}
