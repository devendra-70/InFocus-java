package com.infocus.source_services.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsSourceDTO {
    private String name;
    private List<String> links;
}
