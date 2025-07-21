package com.infocus.source_services.service;

import com.infocus.source_services.dto.NewsSourceDTO;
import com.infocus.source_services.dto.NewsSourceResponseDTO;

import java.util.List;

public interface NewsSourceService {
    NewsSourceResponseDTO addSource(NewsSourceDTO dto);
    List<NewsSourceResponseDTO> getAllSources();
    NewsSourceResponseDTO updateSource(Long id, NewsSourceDTO dto);
    void deleteSource(Long id);
}
