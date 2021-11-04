package br.com.eventhorizon.demo.context;

import java.util.concurrent.Callable;

public class ContextAwareTask<T> implements Runnable, Callable<T> {

  private final Object delegate;

  protected final Context context;

  private ContextAwareTask(Object delegate) {
    this.delegate = delegate;
    this.context = Context.get();
  }

  public static Runnable wrap(Runnable runnable) {
    return new ContextAwareTask<>(runnable);
  }

  public static <V> Callable<V> wrap(Callable<V> callable) {
    return new ContextAwareTask<>(callable);
  }

  @Override
  public void run() {
    context.reload();
    ((Runnable)delegate).run();
  }

  @Override
  public T call() throws Exception {
    context.reload();
    return ((Callable<T>)delegate).call();
  }
}
