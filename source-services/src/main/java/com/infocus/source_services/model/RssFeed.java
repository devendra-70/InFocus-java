package com.infocus.source_services.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "rss_feeds",
        uniqueConstraints = @UniqueConstraint(columnNames = {"region", "category", "url"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RssFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 500)
    private String url;
}
