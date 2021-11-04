package br.com.eventhorizon.demo.properties.sources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class ReloadablePropertySource extends MapPropertySource {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReloadablePropertySource.class);

  private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  public ReloadablePropertySource(String name) {
    super(name, new HashMap<>());
    init(10, 5, TimeUnit.SECONDS);
  }

  public void init(long initialDelay, long period, TimeUnit timeUnit) {
    LOGGER.info("Initializing reloadable property source '" + name + "' with initial delay {} and period of {} {}",
        initialDelay, period, timeUnit);
    executor.scheduleAtFixedRate(this::reloadProperties, initialDelay, period, timeUnit);
  }

  public abstract void reloadProperties();
}
