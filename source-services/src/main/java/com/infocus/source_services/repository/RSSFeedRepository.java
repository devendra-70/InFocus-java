package com.source.repository;

import com.source.entity.RssFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RssFeedRepository extends JpaRepository<RssFeed, Long> {
    Optional<RssFeed> findByRegionAndCategoryAndUrl(String region, String category, String url);
}
