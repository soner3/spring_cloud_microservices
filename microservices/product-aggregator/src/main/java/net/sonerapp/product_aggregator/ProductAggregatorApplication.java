package net.sonerapp.product_aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductAggregatorApplication.class, args);
	}

}
