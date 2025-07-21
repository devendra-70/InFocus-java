package com.dev.sourceservice.controller;

import com.dev.sourceservice.dto.NewsSourceDTO;
import com.dev.sourceservice.dto.NewsSourceResponseDTO;
import com.dev.sourceservice.service.NewsSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
@RequiredArgsConstructor
public class NewsSourceController {

    private final NewsSourceService newsSourceService;

    @PostMapping
    public ResponseEntity<NewsSourceResponseDTO> addSource(@RequestBody NewsSourceDTO dto) {
        return ResponseEntity.ok(newsSourceService.addSource(dto));
    }

    @GetMapping
    public ResponseEntity<List<NewsSourceResponseDTO>> getAllSources() {
        return ResponseEntity.ok(newsSourceService.getAllSources());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsSourceResponseDTO> updateSource(@PathVariable Long id, @RequestBody NewsSourceDTO dto) {
        return ResponseEntity.ok(newsSourceService.updateSource(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        newsSourceService.deleteSource(id);
        return ResponseEntity.noContent().build();
    }
}
