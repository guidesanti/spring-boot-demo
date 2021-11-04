package br.com.eventhorizon.demo.context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ContextAwareExecutors {

  private ContextAwareExecutors() { }

  public static ExecutorService fixedThreadPool(int poolSize) {
    return new ContextAwareExecutorService(Executors.newFixedThreadPool(poolSize));
  }

  public static ExecutorService singleThreadScheduledExecutor() {
    return new ContextAwareExecutorService(Executors.newSingleThreadScheduledExecutor());
  }
}
