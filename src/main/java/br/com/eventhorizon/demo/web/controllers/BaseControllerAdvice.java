package br.com.eventhorizon.demo.web.controllers;

import br.com.eventhorizon.demo.web.dto.Response;
import br.com.eventhorizon.demo.web.dto.ResponseError;
import br.com.eventhorizon.demo.web.dto.ResponseErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BaseControllerAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerAdvice.class);

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected ResponseEntity<Response> internalServerError(Exception ex) {
    LOGGER.error(ex.getMessage(), ex);
    return ResponseEntity.internalServerError()
        .body(Response.builder(br.com.eventhorizon.demo.web.dto.ResponseStatus.SERVER_ERROR)
            .error(ResponseError.builder()
                .code(ResponseErrorCode.UNKNOWN.getValue())
                .message(ex.getMessage())
                .build())
            .build());
  }
}
