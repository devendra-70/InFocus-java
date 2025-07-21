package com.infocus.source_services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for returning news source details including ID and RSS links.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsSourceResponseDTO {
    private Long id;
    private String name;
    private List<String> links;
}
