package com.learn.ib.song_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.ib.song_service.model.dto.MetadataValidationDetailsDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {
    private String errorMessage;
    private int errorCode;
    private MetadataValidationDetailsDto details;

    public ErrorResponseDto(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ErrorResponseDto(String errorMessage, int errorCode, MetadataValidationDetailsDto details) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.details = details;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public MetadataValidationDetailsDto getDetails() {
        return details;
    }

    public void setDetails(MetadataValidationDetailsDto details) {
        this.details = details;
    }
}
