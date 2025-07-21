package com.infocus.source_services.repository;

import com.infocus.source_services.model.RSSLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RSSLinkRepository extends JpaRepository<RSSLink, Long> {
    List<RSSLink> findBySourceId(Long sourceId);  // Optional but useful
}
