package com.source.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rss_feeds", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"region", "category", "url"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RssFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String region;
    private String category;
    private String url;
}
