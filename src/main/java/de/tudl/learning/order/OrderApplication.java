package de.tudl.learning.order;

import de.tudl.learning.order.exception.ProductServiceEventErrorHandler;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer)
	{
		configurer.registerListenerInvocationErrorHandler(
				"product",
				configuration -> new ProductServiceEventErrorHandler()
		);
	}
}
