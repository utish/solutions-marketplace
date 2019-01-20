package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Marketplace;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Marketplace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {

}
