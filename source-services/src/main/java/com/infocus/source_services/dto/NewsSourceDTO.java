package com.infocus.source_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for receiving a news source with its RSS links.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsSourceDTO {
    private String name;
    private List<String> links;
}
