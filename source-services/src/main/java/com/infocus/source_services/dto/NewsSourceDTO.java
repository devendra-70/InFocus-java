package com.dev.sourceservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsSourceDTO {
    private String name;
    private List<String> links;
}
