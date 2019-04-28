package com.chris.mobile_subscribers.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ValidationErrorResponseDto implements Serializable {
    private static final long serialVersionUID = -8612583664006981096L;
    private String error;
}
