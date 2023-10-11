package com.statistics.vector.service;

import java.util.Map;

/**
 * Interface to process vector data
 *
 */

public interface VectorDataService {

	Map<String, Double> calculateStatistics(Long id);

	Long generateAndSaveRandomVector();
	
	
}
