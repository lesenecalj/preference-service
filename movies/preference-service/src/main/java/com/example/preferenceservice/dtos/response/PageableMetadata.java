package com.example.preferenceservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageableMetadata {
    private int current;
    private int size;
    private long totalElements;
    private int totalPages;
}
