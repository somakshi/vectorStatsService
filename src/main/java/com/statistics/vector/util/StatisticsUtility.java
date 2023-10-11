package com.statistics.vector.util;

import java.util.List;

/**
 * Utility class for statistics
 *
 */
public class StatisticsUtility {
	
	/**
	 * Method to calculate mean of numbers.
	 * 
	 */
	public static double calculateMean(List<Integer> vector) {
		return vector.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
	}

	/**
	 * Method to calculate standard deviation of numbers 
	 * 
	 */
	public static double calculateStandardDeviation(List<Integer> vector) {
		double mean = calculateMean(vector);
		double sum = vector.stream().mapToDouble(num -> Math.pow(num - mean, 2)).sum();
		return Math.sqrt(sum / (vector.size() - 1));
	}

}
