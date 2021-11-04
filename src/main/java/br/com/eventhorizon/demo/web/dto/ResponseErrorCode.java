package br.com.eventhorizon.demo.web.dto;

public enum ResponseErrorCode {

  UNKNOWN(0);

  private final int value;

  ResponseErrorCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
