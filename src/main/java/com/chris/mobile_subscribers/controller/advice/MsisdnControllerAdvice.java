package com.chris.mobile_subscribers.controller.advice;

import com.chris.mobile_subscribers.controller.MsisdnHistoricController;
import com.chris.mobile_subscribers.controller.MsisdnManagementController;
import com.chris.mobile_subscribers.controller.MsisdnQueryController;
import com.chris.mobile_subscribers.dto.ValidationErrorResponseDto;
import com.chris.mobile_subscribers.exception.GlobalException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice(assignableTypes = {MsisdnHistoricController.class, MsisdnManagementController.class, MsisdnQueryController.class})
public class MsisdnControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String globalExceptionHandling(GlobalException exception) {
        return exception.getLocalizedMessage();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {


        List<CustomFieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new CustomFieldError(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getRejectedValue())
                )
                .collect(toList());


        return new ResponseEntity(ValidationErrorResponseDto.builder().error(fieldErrors.toString()).build(), HttpStatus.BAD_REQUEST);
    }

}

@Data
@AllArgsConstructor
class CustomFieldError {
    private String field;
    private String code;
    private Object rejectedValue;
}
