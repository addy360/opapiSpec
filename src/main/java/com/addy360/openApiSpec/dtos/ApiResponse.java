package com.addy360.openApiSpec.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {
    String message;
    Object data;
}
