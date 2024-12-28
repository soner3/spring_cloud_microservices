package net.sonerapp.product_aggregator.service.client;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import net.sonerapp.product_aggregator.dto.recommendation.RecommendationDto;

@HttpExchange
public interface RecommendationService {

    @GetExchange("/v1/recommendation/{productId}")
    ResponseEntity<List<RecommendationDto>> getProductRecommendation(@PathVariable UUID productId);

}
