package net.sonerapp.recommendation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sonerapp.recommendation.dto.RecommendationDto;

public interface RecommendationController {

    @GetMapping(value = "/recommendation/{productId}")
    public ResponseEntity<List<RecommendationDto>> getRecommendations(@PathVariable int productId);

}