package br.com.eventhorizon.demo;

import br.com.eventhorizon.demo.context.ContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	@Bean
	public FilterRegistrationBean<ContextFilter> directoryContextFilter() {
		FilterRegistrationBean<ContextFilter> reg = new FilterRegistrationBean<>();
		reg.setFilter(new ContextFilter());
//		reg.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
		reg.setName("contextFilter");
		return reg;
	}

	public static void main(String[] args) {
//		try {
//			LOGGER.info("Waiting for debugger to attach ...");
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			LOGGER.error("Thread interrupted while waiting for debugger to attach", e);
//		}
		SpringApplication.run(DemoApplication.class, args);
	}
}
