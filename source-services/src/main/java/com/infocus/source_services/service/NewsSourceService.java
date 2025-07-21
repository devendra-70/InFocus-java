package com.dev.sourceservice.service;

import com.dev.sourceservice.dto.NewsSourceDTO;
import com.dev.sourceservice.dto.NewsSourceResponseDTO;

import java.util.List;

public interface NewsSourceService {
    NewsSourceResponseDTO addSource(NewsSourceDTO dto);
    List<NewsSourceResponseDTO> getAllSources();
    NewsSourceResponseDTO updateSource(Long id, NewsSourceDTO dto);
    void deleteSource(Long id);
}
