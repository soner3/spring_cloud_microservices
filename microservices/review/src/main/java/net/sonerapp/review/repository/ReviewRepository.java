package net.sonerapp.review.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    boolean existsByReviewId(int reviewId);

    List<Review> findByProductId(int productId);

}
