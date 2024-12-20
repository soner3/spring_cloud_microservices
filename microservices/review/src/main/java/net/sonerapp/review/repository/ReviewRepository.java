package net.sonerapp.review.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByReviewId(UUID reviewId);

    List<Review> findByProductId(UUID productId);

    int deleteByReviewId(UUID reviewId);

    Optional<Review> findByReviewId(UUID reviewId);

}
