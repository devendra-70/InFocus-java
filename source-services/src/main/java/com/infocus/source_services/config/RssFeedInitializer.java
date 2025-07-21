package com.source.config;

import com.infocus.source_services.model.RssFeed;
import com.source.repository.RssFeedRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
@RequiredArgsConstructor
public class RssFeedInitializer {

    private final RssFeedRepository rssFeedRepository;

    @PostConstruct
    public void loadRssFeeds() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("rss_links.txt")))) {

            reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> {
                        String[] parts = line.split("\\s+", 3);
                        if (parts.length == 3) {
                            String region = parts[0];
                            String category = parts[1];
                            String url = parts[2];

                            boolean exists = rssFeedRepository
                                    .findByRegionAndCategoryAndUrl(region, category, url)
                                    .isPresent();

                            if (!exists) {
                                RssFeed feed = RssFeed.builder()
                                        .region(region)
                                        .category(category)
                                        .url(url)
                                        .build();
                                rssFeedRepository.save(feed);
                            }
                        }
                    });

        } catch (Exception e) {
            System.err.println("Error reading rss_links.txt: " + e.getMessage());
        }
    }
}
