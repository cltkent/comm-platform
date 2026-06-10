package com.clt.toscana.history;

import com.clt.toscana.model.entity.RealtimeEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for RealtimeEvent entities.
 */
@Repository
public interface RealtimeEventRepository extends JpaRepository<RealtimeEvent, Long> {
    Page<RealtimeEvent> findByClientIdOrderByTimestampDesc(String clientId, Pageable pageable);

    Page<RealtimeEvent> findAllByOrderByTimestampDesc(Pageable pageable);
}
