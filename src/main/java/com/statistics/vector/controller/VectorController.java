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

import com.statistics.vector.constants.Constants;
import com.statistics.vector.service.VectorDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Vector statistics API")
public class VectorController {

	private final Logger logger = LoggerFactory.getLogger(VectorController.class);

	private final VectorDataService vectorService;

	@Autowired
	public VectorController(VectorDataService vectorDataService) {
		this.vectorService = vectorDataService;
	}

	/*
	 * Endpoint to calculate mean and standard deviation for given vector
	 * fetched from DB
	 * 
	 * @param vector ID
	 * 
	 * @return Map<String, Double> containing mean and Standard Deviation
	 */
	@GetMapping("vector/{id}/statistics")
	@ApiOperation("Fetches statistics for a vector")
	public ResponseEntity<Map<String, Double>> getStatistics(
			@PathVariable @NotBlank @Pattern(regexp = "\\S") @ApiParam(value = " ID of the vector to be fetched") Long id) {
		logger.info(Constants.LOG_REQUEST_RECEIVED, id);
		Map<String, Double> statistics = vectorService.calculateStatistics(id);
		logger.info(Constants.LOG_COMPLETED);
		return ResponseEntity.ok(statistics);
	}

	/**
	 * Endpoint to generate a vector containing 2000 random numbers
	 * 
	 * @return Response Entity containing vector ID
	 */
	@PostMapping("vector/generate")
	@ApiOperation("Generates a vector and returns vector ID")
	public ResponseEntity<Long> generateAndSaveVector() {
		logger.info(Constants.LOG_GENERATE_SAVE);
		Long vectorId = vectorService.generateAndSaveRandomVector();
		logger.info(Constants.LOG_VECTOR_SAVED, vectorId);
		return ResponseEntity.ok(vectorId);
	}

}
