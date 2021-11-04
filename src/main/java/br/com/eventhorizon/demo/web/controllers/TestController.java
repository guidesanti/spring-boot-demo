package br.com.eventhorizon.demo.web.controllers;

import br.com.eventhorizon.demo.properties.live.TestProperties;
import br.com.eventhorizon.demo.web.dto.Response;
import br.com.eventhorizon.demo.web.dto.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
  
  private final TestProperties testProperties;

  @Autowired
  public TestController(TestProperties testProperties) {
    this.testProperties = testProperties;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<Response> get() {
    return new ResponseEntity<>(Response.builder(ResponseStatus.SUCCESS).data(testProperties).build(), HttpStatus.OK);
  }
}
