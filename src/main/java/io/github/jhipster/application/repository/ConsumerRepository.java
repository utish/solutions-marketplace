package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Consumer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Consumer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

}
