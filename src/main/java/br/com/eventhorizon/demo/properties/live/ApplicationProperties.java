package br.com.eventhorizon.demo.properties.live;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "br.com.eventhorizon.demo")
public class ApplicationProperties {

  private String property1 = "some default value";

  public String getProperty1() {
    return property1;
  }

  public void setProperty1(String property1) {
    this.property1 = property1;
  }
}
