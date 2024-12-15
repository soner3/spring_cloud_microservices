package net.sonerapp.recommendation.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.sonerapp.recommendation.controller.RecommendationController;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.exception.InvalidInputException;

@RestController
@Slf4j
public class RecommendationControllerImpl implements RecommendationController {

    @Override
    public ResponseEntity<List<RecommendationDto>> getRecommendations(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }

        if (productId == 113) {
            log.debug("No recommendations found for productId: {}", productId);
            return ResponseEntity.ok(new ArrayList<>());
        }

        List<RecommendationDto> list = new ArrayList<>();
        list.add(new RecommendationDto(productId, 1, "Author 1", 1, "Content 1"));
        list.add(new RecommendationDto(productId, 2, "Author 2", 2, "Content 2"));
        list.add(new RecommendationDto(productId, 3, "Author 3", 3, "Content 3"));

        log.debug("/recommendation response size: {}", list.size());

        return ResponseEntity.ok(list);
    }

}
