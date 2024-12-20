package net.sonerapp.recommendation.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.sonerapp.recommendation.entity.Recommendation;

public interface RecommendationRepository extends MongoRepository<Recommendation, ObjectId> {

    boolean existsByRecommendationId(UUID recommendationId);

    List<Recommendation> findByProductId(UUID productId);

    Optional<Recommendation> findByRecommendationId(UUID recommendationId);

    int deleteByRecommendationId(UUID recommendationId);

}
