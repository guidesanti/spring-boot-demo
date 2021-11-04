package br.com.eventhorizon.demo.web.controllers;

import br.com.eventhorizon.demo.properties.live.ApplicationProperties;
import br.com.eventhorizon.demo.properties.live.ManifestProperties;
import br.com.eventhorizon.demo.web.dto.Health;
import br.com.eventhorizon.demo.web.dto.HealthStatus;
import br.com.eventhorizon.demo.web.dto.Response;
import br.com.eventhorizon.demo.web.dto.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

  private final ApplicationProperties properties;

  private final ManifestProperties manifestProperties;

  @Autowired
  public HealthController(ApplicationProperties properties, ManifestProperties manifestProperties) {
    this.properties = properties;
    this.manifestProperties = manifestProperties;
  }


  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<Response> get() {
    Health health = new Health(HealthStatus.UP);
    health.setVersion(manifestProperties.getVersion());
    health.setGitHash(manifestProperties.getGitHash());
    health.setBuild(manifestProperties.getBuild());
    return new ResponseEntity<>(Response.builder(ResponseStatus.SUCCESS).data(health).build(), HttpStatus.OK);
  }
}
