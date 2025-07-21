package com.infocus.source_services.controller;

import com.infocus.source_services.dto.NewsSourceDTO;
import com.infocus.source_services.dto.NewsSourceResponseDTO;
import com.infocus.source_services.service.NewsSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing news sources.
 */
@RestController
@RequestMapping("/api/sources")
@RequiredArgsConstructor
public class NewsSourceController {

    private final NewsSourceService newsSourceService;

    /**
     * Adds a new news source.
     * @param dto the source data
     * @return the saved source
     */
    @PostMapping
    public ResponseEntity<NewsSourceResponseDTO> addSource(@RequestBody NewsSourceDTO dto) {
        NewsSourceResponseDTO response = newsSourceService.addSource(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all news sources.
     * @return list of all sources
     */
    @GetMapping
    public ResponseEntity<List<NewsSourceResponseDTO>> getAllSources() {
        return ResponseEntity.ok(newsSourceService.getAllSources());
    }

    /**
     * Updates an existing source by ID.
     * @param id  the source ID
     * @param dto the updated data
     * @return the updated source
     */
    @PutMapping("/{id}")
    public ResponseEntity<NewsSourceResponseDTO> updateSource(
            @PathVariable Long id,
            @RequestBody NewsSourceDTO dto
    ) {
        NewsSourceResponseDTO updated = newsSourceService.updateSource(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a source by ID.
     * @param id the source ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        newsSourceService.deleteSource(id);
        return ResponseEntity.noContent().build();
    }
}
