package net.sonerapp.review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import net.sonerapp.review.entity.Review;
import net.sonerapp.review.repository.ReviewRepository;

@SpringBootApplication
@EnableJpaAuditing
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ReviewRepository reviewRepository) {
		return args -> {
			if (!reviewRepository.existsByReviewId(1)) {
				reviewRepository.save(new Review(1, 3, "Author 1", "Subject 1", "Content 1"));
				reviewRepository.save(new Review(2, 3, "Author 2", "Subject 2", "Content 2"));
				reviewRepository.save(new Review(3, 3, "Author 3", "Subject 3", "Content 3"));

			}
		};
	}

}
