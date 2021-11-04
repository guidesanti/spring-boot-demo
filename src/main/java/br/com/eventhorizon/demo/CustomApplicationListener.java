package br.com.eventhorizon.demo;

import br.com.eventhorizon.demo.properties.PropertyManager;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class CustomApplicationListener implements ApplicationListener<ApplicationEvent>  {

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ApplicationEnvironmentPreparedEvent) {
      PropertyManager.init(((ApplicationEnvironmentPreparedEvent) event).getEnvironment());
    } else if (event instanceof EnvironmentChangeEvent) {
      // TODO
      EnvironmentChangeEvent evt = (EnvironmentChangeEvent) event;
      int a = 10;
    }
  }
}
