package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Marketplace.
 */
@Entity
@Table(name = "marketplace")
public class Marketplace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "marketplace")
    private Set<Producer> producers = new HashSet<>();
    @OneToMany(mappedBy = "marketplace")
    private Set<Consumer> consumers = new HashSet<>();
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

    public Marketplace name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Producer> getProducers() {
        return producers;
    }

    public Marketplace producers(Set<Producer> producers) {
        this.producers = producers;
        return this;
    }

    public Marketplace addProducer(Producer producer) {
        this.producers.add(producer);
        producer.setMarketplace(this);
        return this;
    }

    public Marketplace removeProducer(Producer producer) {
        this.producers.remove(producer);
        producer.setMarketplace(null);
        return this;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }

    public Set<Consumer> getConsumers() {
        return consumers;
    }

    public Marketplace consumers(Set<Consumer> consumers) {
        this.consumers = consumers;
        return this;
    }

    public Marketplace addConsumer(Consumer consumer) {
        this.consumers.add(consumer);
        consumer.setMarketplace(this);
        return this;
    }

    public Marketplace removeConsumer(Consumer consumer) {
        this.consumers.remove(consumer);
        consumer.setMarketplace(null);
        return this;
    }

    public void setConsumers(Set<Consumer> consumers) {
        this.consumers = consumers;
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
        Marketplace marketplace = (Marketplace) o;
        if (marketplace.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), marketplace.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Marketplace{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
