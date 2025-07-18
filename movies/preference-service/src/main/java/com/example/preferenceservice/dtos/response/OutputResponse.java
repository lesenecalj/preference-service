package com.example.preferenceservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutputResponse<T> {
    private T data;
}
