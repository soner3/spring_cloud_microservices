package net.sonerapp.recommendation.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.sonerapp.recommendation.entity.Recommendation;

public interface RecommendationRepository extends MongoRepository<Recommendation, ObjectId> {

    boolean existsByRecommendationId(int recommendationId);

    List<Recommendation> findByProductId(int productId);

}
