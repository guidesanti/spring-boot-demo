package br.com.eventhorizon.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

  @JsonProperty("status")
  private ResponseStatus status;

  @JsonProperty("data")
  private Object data;

  @JsonProperty("error")
  private ResponseError error;

  private Response(ResponseStatus status, Object data, ResponseError error) {
    this.status = status;
    this.data = data;
    this.error = error;
  }

  public ResponseStatus getStatus() {
    return status;
  }

  public Object getData() {
    return data;
  }

  public ResponseError getError() {
    return error;
  }

  public static Builder builder(ResponseStatus status) {
    Builder builder = new Builder();
    builder.status = status;
    return builder;
  }

  public static class Builder {

    private ResponseStatus status;

    private Object data;

    private ResponseError error;

    private Builder() { }

    public Builder data(Object data) {
      this.data = data;
      return this;
    }

    public Builder error(ResponseError error) {
      this.error = error;
      return this;
    }

    public Response build() {
      return new Response(status, data, error);
    }
  }
}
