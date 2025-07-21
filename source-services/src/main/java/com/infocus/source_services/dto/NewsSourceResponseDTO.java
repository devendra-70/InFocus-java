package com.dev.sourceservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewsSourceResponseDTO {
    private Long id;
    private String name;
    private List<String> links;
}
