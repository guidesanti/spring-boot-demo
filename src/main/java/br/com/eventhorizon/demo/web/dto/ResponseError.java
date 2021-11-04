package br.com.eventhorizon.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

  @JsonProperty("code")
  private Integer code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("details")
  private String details;

  private ResponseError(Integer code, String message, String details) {
    this.code = code;
    this.message = message;
    this.details = details;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Integer code;

    private String message;

    private String details;

    private Builder() {
      this.code = ResponseErrorCode.UNKNOWN.getValue();
    }

    public Builder code(int code) {
      this.code = code;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder details(String details) {
      this.details = details;
      return this;
    }

    public ResponseError build() {
      return new ResponseError(code, message,details);
    }
  }
}
