package br.com.eventhorizon.demo.context;

import br.com.eventhorizon.demo.web.dto.HttpHeaderKey;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class ContextFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    try {
      HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
      String traceId = httpRequest.getHeader(HttpHeaderKey.X_TRACE_ID);
      Context.create(traceId != null ? traceId : UUID.randomUUID().toString());
      filterChain.doFilter(servletRequest, servletResponse);
    } finally {
      Context.clear();
    }
  }
}
