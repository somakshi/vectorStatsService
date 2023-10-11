package com.statistics.vector.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statistics.vector.entity.VectorData;
import com.statistics.vector.exceptions.VectorNotFoundException;
import com.statistics.vector.repository.VectorDataRepository;
import com.statistics.vector.service.VectorDataService;
import com.statistics.vector.util.StatisticsUtility;

/**
 * Implementation service class for vector data
 *
 */
@Service
public class VectorDataServiceImpl implements VectorDataService {

	@Autowired
	private VectorDataRepository vectorDataRepository;

	@Override
	public Map<String, Double> calculateStatistics(Long id) {
		Optional<VectorData> vectorData = vectorDataRepository.findById(id);
		if (!vectorData.isPresent()) {
			throw new VectorNotFoundException("Vector with ID " + id + " not found");
		}
		String numbers = vectorData.get().getNumbers();
		String[] numberStrings = numbers.split(",");
		List<Integer> vector = Arrays.stream(numberStrings).map(Integer::parseInt).collect(Collectors.toList());

		double mean = StatisticsUtility.calculateMean(vector);
		double standardDeviation = StatisticsUtility.calculateStandardDeviation(vector);

		Map<String, Double> result = new HashMap<>();
		result.put("mean", mean);
		result.put("standardDeviation", standardDeviation);

		return result;
	}

	@Override
	public Long generateAndSaveRandomVector() {
		 List<Integer> randomNumbers = new Random().ints(2000, 1, 101).boxed().collect(Collectors.toList());
	        String numbersAsString = randomNumbers.stream()
	            .map(String::valueOf)
	            .collect(Collectors.joining(","));

	        VectorData vector = new VectorData();
	        vector.setNumbers(numbersAsString);
	        VectorData savedVector = vectorDataRepository.save(vector);
	        return savedVector.getId();
	}

	

}
