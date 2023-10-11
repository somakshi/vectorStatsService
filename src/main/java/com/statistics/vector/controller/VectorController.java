package com.statistics.vector.controller;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.statistics.vector.service.VectorDataService;

@RestController
public class VectorController {

	private final Logger logger = LoggerFactory.getLogger(VectorController.class);

	private final VectorDataService vectorService;

	@Autowired
	public VectorController(VectorDataService vectorDataService) {
		this.vectorService = vectorDataService;
	}

	/*
	 * Endpoint to calculate mean and standard deviation for given vector fetched from DB
	 * @param vector ID
	 * @return Map<String, Double> containing mean and Standard Deviation
	 */
	@GetMapping("vector/{id}/statistics")
	public ResponseEntity<Map<String, Double>> getStatistics(@PathVariable @NotBlank @Pattern(regexp = "\\S") Long id) {
		logger.info("Received request for statistics with ID: {}", id);
		Map<String, Double> statistics = vectorService.calculateStatistics(id);
		logger.info("Statistics calculation completed.");
		return ResponseEntity.ok(statistics);
	}

	/**
	 * Endpoint to generate a vector containing 2000 random numbers
	 * 
	 * @return Response Entity containing vector ID
	 */
	@PostMapping("vector/generate")
	public ResponseEntity<Long> generateAndSaveVector() {
		logger.info("Generating and saving a random vector...");
		Long vectorId = vectorService.generateAndSaveRandomVector();
		logger.info("Random vector saved with ID: {}", vectorId);
		return ResponseEntity.ok(vectorId);
	}

}
