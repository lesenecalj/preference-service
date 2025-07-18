package com.example.preferenceservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageableResponse<T> {
    private List<T> data;
    private PageableMetadata pageableMetadata;
}
