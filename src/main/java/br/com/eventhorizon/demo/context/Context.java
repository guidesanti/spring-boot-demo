package br.com.eventhorizon.demo.context;

import br.com.eventhorizon.demo.tracing.MDCKey;
import org.slf4j.MDC;

import java.util.UUID;

public final class Context {

  private static final ThreadLocal<Context> context = new ThreadLocal<>();

  private final String contextId;

  private final String traceId;

  private Context(String traceId) {
    this.contextId = UUID.randomUUID().toString();
    this.traceId = traceId;
  }

  public static Context create(String traceId) {
    MDC.put(MDCKey.TRACE_ID, traceId);
    Context ctx = new Context(traceId);
    context.set(ctx);
    return ctx;
  }

  public static Context get() {
    return context.get();
  }

  public void reload() {
    context.set(this);
  }

  public static void clear() {
    MDC.remove(MDCKey.TRACE_ID);
    context.remove();
  }

  public static String getContextId() {
    return context.get().contextId;
  }

  public static String getTraceId() {
    return context.get().traceId;
  }

  @Override
  public String toString() {
    return "Context{" + "contextId='" + contextId + '\'' + ", traceId='" + traceId + '\'' + '}';
  }
}
