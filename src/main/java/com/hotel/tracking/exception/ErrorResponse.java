package com.hotel.tracking.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Standard error response")
public class ErrorResponse {
    @Schema(description = "HTTP status code of the error", example = "400")
    private int status;

    @Schema(description = "Description of the error", example = "Invalid page number or page size")
    private String message;
}
