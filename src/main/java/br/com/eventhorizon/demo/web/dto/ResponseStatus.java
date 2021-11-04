package br.com.eventhorizon.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStatus {

  SUCCESS("Success"),
  CLIENT_ERROR("ClientError"),
  SERVER_ERROR("ServerError");

  private final String value;

  ResponseStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
