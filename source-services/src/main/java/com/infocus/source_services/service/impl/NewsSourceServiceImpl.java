package com.dev.sourceservice.service.impl;

import com.dev.sourceservice.dto.NewsSourceDTO;
import com.dev.sourceservice.dto.NewsSourceResponseDTO;
import com.dev.sourceservice.exception.ResourceNotFoundException;
import com.dev.sourceservice.model.NewsSource;
import com.dev.sourceservice.model.RSSLink;
import com.dev.sourceservice.repository.NewsSourceRepository;
import com.dev.sourceservice.repository.RSSLinkRepository;
import com.dev.sourceservice.service.NewsSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsSourceServiceImpl implements NewsSourceService {

    private final NewsSourceRepository newsSourceRepository;
    private final RSSLinkRepository rssLinkRepository;

    @Override
    @Transactional
    public NewsSourceResponseDTO addSource(NewsSourceDTO dto) {
        if (newsSourceRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("A source with this name already exists.");
        }

        NewsSource source = NewsSource.builder()
                .name(dto.getName())
                .build();

        source = newsSourceRepository.save(source);

        List<RSSLink> links = dto.getLinks().stream()
                .filter(url -> !url.isBlank())
                .map(url -> RSSLink.builder()
                        .source(source)
                        .url(url)
                        .build())
                .collect(Collectors.toList());

        rssLinkRepository.saveAll(links);
        source.setLinks(links); // if you want to reflect them in response

        return mapToResponseDTO(source);
    }

    @Override
    public List<NewsSourceResponseDTO> getAllSources() {
        return newsSourceRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NewsSourceResponseDTO updateSource(Long id, NewsSourceDTO dto) {
        NewsSource source = newsSourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Source with ID " + id + " not found."));

        // Name uniqueness validation
        if (!source.getName().equals(dto.getName()) && newsSourceRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Another source with this name already exists.");
        }

        // Update name
        source.setName(dto.getName());

        // Delete existing links
        List<RSSLink> oldLinks = source.getLinks();
        if (oldLinks != null && !oldLinks.isEmpty()) {
            rssLinkRepository.deleteAll(oldLinks);
        }

        // Save new links
        List<RSSLink> newLinks = dto.getLinks().stream()
                .filter(url -> !url.isBlank())
                .map(url -> RSSLink.builder()
                        .source(source)
                        .url(url)
                        .build())
                .collect(Collectors.toList());

        rssLinkRepository.saveAll(newLinks);
        source.setLinks(newLinks);

        return mapToResponseDTO(newsSourceRepository.save(source));
    }

    @Override
    @Transactional
    public void deleteSource(Long id) {
        NewsSource source = newsSourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Source with ID " + id + " not found."));
        newsSourceRepository.delete(source);
    }

    private NewsSourceResponseDTO mapToResponseDTO(NewsSource source) {
        return NewsSourceResponseDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .links(source.getLinks().stream()
                        .map(RSSLink::getUrl)
                        .toList())
                .build();
    }
}
