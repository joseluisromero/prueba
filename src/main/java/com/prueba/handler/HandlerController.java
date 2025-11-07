package com.prueba.handler;

import com.prueba.service.dto.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class HandlerController {
    protected ErrorInfo warnAndRespond(String code, String message) {
        log.warn(message);
        return ErrorInfo.builder().code(code).message(message).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorInfo handlerInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        StringBuilder message = new StringBuilder();
        errorMap.forEach((s, s2) -> {
            message.append(s + " " + s2 + ",");
        });

        return ErrorInfo.builder().code(HttpStatus.BAD_REQUEST.toString()).message(message.toString()).build();
    }

    @ExceptionHandler({HttpMessageNotWritableException.class})
    public ErrorInfo httpMessageNotWritableExceptionHandler(HttpMessageNotWritableException httpMessageNotWritableException) {
        log.error(httpMessageNotWritableException.getMessage());

        return warnAndRespond(HttpStatus.NOT_FOUND.toString(), httpMessageNotWritableException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericException(Exception exception) {
        log.error("GenericException: {} ", exception.getMessage(), exception);
        return new ResponseEntity<>(ErrorInfo.builder().message(exception.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
