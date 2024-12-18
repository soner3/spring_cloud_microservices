package net.sonerapp.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import net.sonerapp.product.entity.Product;
import net.sonerapp.product.repository.ProductRepository;

@SpringBootApplication
@EnableMongoAuditing
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ProductRepository productRepository) {
		return args -> {
			if (!productRepository.existsByProductId(3)) {
				productRepository.save(new Product(3, "Product-" + 3, 123));
			}
		};
	}

}
