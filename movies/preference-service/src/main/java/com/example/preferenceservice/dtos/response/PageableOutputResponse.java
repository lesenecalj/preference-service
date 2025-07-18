package com.example.preferenceservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageableOutputResponse<T> {
    private List<T> data;
    private PageableMetadata pageableMetadata;
}
