package com.ludoteca.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;


import java.time.Instant;
import java.util.List;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    Instant timestamp;
    int status;
    String error;
    String code;
    String message;
    String path;
    String traceId;
    List<FieldErrorItem> fieldErrors;

    @Value
    @Builder
    public static class FieldErrorItem {
        String field;
        String message;
    }
}
