package com.infocus.source_services.repository;

import com.infocus.source_services.model.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {

    boolean existsByName(String name);

    Optional<NewsSource> findByName(String name);
}
