package com.dev.sourceservice.repository;

import com.dev.sourceservice.model.RSSLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RssFeedRepository extends JpaRepository<RSSLink, Long> {
}
