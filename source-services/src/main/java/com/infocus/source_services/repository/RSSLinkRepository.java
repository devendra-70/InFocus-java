package com.infocus.source_services.repository;

import com.infocus.source_services.model.RSSLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RSSLinkRepository extends JpaRepository<RSSLink, Long> {
    // You can add custom queries here if needed later (e.g., findBySourceId)
}
