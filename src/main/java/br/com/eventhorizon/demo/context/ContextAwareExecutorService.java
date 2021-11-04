package br.com.eventhorizon.demo.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ContextAwareExecutorService implements ExecutorService {

  private final ExecutorService delegate;

  public ContextAwareExecutorService(ExecutorService delegate) {
    this.delegate = delegate;
  }

  @Override
  public void shutdown() {
    delegate.shutdown();
  }

  @Override
  public List<Runnable> shutdownNow() {
    return delegate.shutdownNow();
  }

  @Override
  public boolean isShutdown() {
    return delegate.isShutdown();
  }

  @Override
  public boolean isTerminated() {
    return delegate.isTerminated();
  }

  @Override
  public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
    return delegate.awaitTermination(timeout, unit);
  }

  @Override
  public <T> Future<T> submit(Callable<T> task) {
    return delegate.submit(ContextAwareTask.wrap(task));
  }

  @Override
  public <T> Future<T> submit(Runnable task, T result) {
    return delegate.submit(ContextAwareTask.wrap(task), result);
  }

  @Override
  public Future<?> submit(Runnable task) {
    return delegate.submit(ContextAwareTask.wrap(task));
  }

  @Override
  public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
    Collection<Callable<T>> contextAwareTasks = new ArrayList<>();
    tasks.forEach(task -> contextAwareTasks.add(ContextAwareTask.wrap(task)));
    return delegate.invokeAll(contextAwareTasks);
  }

  @Override
  public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
    Collection<Callable<T>> contextAwareTasks = new ArrayList<>();
    tasks.forEach(task -> contextAwareTasks.add(ContextAwareTask.wrap(task)));
    return delegate.invokeAll(contextAwareTasks, timeout, unit);
  }

  @Override
  public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
    Collection<Callable<T>> contextAwareTasks = new ArrayList<>();
    tasks.forEach(task -> contextAwareTasks.add(ContextAwareTask.wrap(task)));
    return delegate.invokeAny(contextAwareTasks);
  }

  @Override
  public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    Collection<Callable<T>> contextAwareTasks = new ArrayList<>();
    tasks.forEach(task -> contextAwareTasks.add(ContextAwareTask.wrap(task)));
    return delegate.invokeAny(contextAwareTasks, timeout, unit);
  }

  @Override
  public void execute(Runnable command) {
    delegate.execute(ContextAwareTask.wrap(command));
  }
}
