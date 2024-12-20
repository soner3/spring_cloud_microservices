package net.sonerapp.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import net.sonerapp.product.dto.CreateProductDto;
import net.sonerapp.product.service.ProductService;

@SpringBootApplication
@EnableMongoAuditing
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ProductService productService) {
		return args -> {
			productService.createProduct(new CreateProductDto("Product-" + 3, 123, 3.99));
		};
	}

}
