package com.digital.common.exception;

import com.digital.common.enums.ErrorCode;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DigitalApiException {

    private HttpStatus statusCode;

    private ErrorCode errorCode;

    private String message;

    private String description;

    private List<ValidationError> validationErrors;

    public DigitalApiException(HttpStatus statusCode, ErrorCode errorCode) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = "Unexpected error";
        this.description = "No error description provided";
    }

    public DigitalApiException(
            HttpStatus statusCode, ErrorCode errorCode, String message, String description) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
        this.description = description;
    }

    @JsonGetter("statusCode")
    public Integer getStatusCodeValue() {
        return statusCode.value();
    }

    @JsonSetter("statusCode")
    public void setStatusCodeValue(Integer statusCodeValue) {
        statusCode = HttpStatus.valueOf(statusCodeValue);
    }
}
