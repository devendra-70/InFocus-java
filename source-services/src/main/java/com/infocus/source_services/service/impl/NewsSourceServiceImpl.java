package com.infocus.source_services.service.impl;

import com.infocus.source_services.dto.NewsSourceDTO;
import com.infocus.source_services.dto.NewsSourceResponseDTO;
import com.infocus.source_services.exception.ResourceNotFoundException;
import com.infocus.source_services.model.NewsSource;
import com.infocus.source_services.model.RSSLink;
import com.infocus.source_services.repository.NewsSourceRepository;
import com.infocus.source_services.repository.RSSLinkRepository;
import com.infocus.source_services.service.NewsSourceService;
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
        source.setLinks(links);

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

        if (!source.getName().equals(dto.getName()) && newsSourceRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Another source with this name already exists.");
        }

        source.setName(dto.getName());

        List<RSSLink> oldLinks = source.getLinks();
        if (oldLinks != null && !oldLinks.isEmpty()) {
            rssLinkRepository.deleteAll(oldLinks);
