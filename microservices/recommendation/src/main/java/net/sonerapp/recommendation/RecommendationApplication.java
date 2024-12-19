package net.sonerapp.recommendation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import net.sonerapp.recommendation.entity.Recommendation;
import net.sonerapp.recommendation.repository.RecommendationRepository;

@SpringBootApplication
@EnableMongoAuditing
public class RecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(RecommendationRepository recommendationRepository) {
		return args -> {
			if (!recommendationRepository.existsByRecommendationId(1)) {
				recommendationRepository.save(new Recommendation(1, 3, "Author 1", 1, "Content 1"));
				recommendationRepository.save(new Recommendation(2, 3, "Author 2", 2, "Content 2"));
				recommendationRepository.save(new Recommendation(3, 3, "Author 3", 3, "Content 3"));
			}
		};
	}

}
