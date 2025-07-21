package com.infocus.source_services.repository;

import com.dev.sourceservice.model.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
    boolean existsByName(String name);
    Optional<NewsSource> findByName(String name);
}
