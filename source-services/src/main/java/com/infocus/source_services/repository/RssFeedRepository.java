package com.infocus.source_services.repository;

import com.infocus.source_services.model.RssFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssFeedRepository extends JpaRepository<RssFeed, Long> {
    // Add custom query methods as needed
}
