package com.statistics.vector.service.impl.test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.statistics.vector.entity.VectorData;
import com.statistics.vector.repository.VectorDataRepository;
import com.statistics.vector.service.impl.VectorDataServiceImpl;

import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VectorDataServiceImplTest {

	@InjectMocks
	private VectorDataServiceImpl vectorDataService;

	@Mock
	private VectorDataRepository repository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCalculateStatistics() {

		VectorData vectorEntity = new VectorData();
		vectorEntity.setId(1L);
		vectorEntity.setNumbers("1,2,3,4,5,6,7,8,9,10");
		Optional<VectorData> vector = Optional.of(vectorEntity);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(vector);

		Map<String, Double> statistics = vectorDataService.calculateStatistics(1L);

		assertNotNull(statistics);
		
		Assert.assertEquals(new Double(5.5), statistics.get("mean"));
		Assert.assertEquals(new Double(3.0276503540974917), statistics.get("standardDeviation"));
	
	}

	@Test
	public void testGenerateAndSaveRandomVector() {
		VectorData vectorEntity = new VectorData();
		vectorEntity.setId(1L);

		Mockito.when(repository.save(Mockito.any())).thenReturn(vectorEntity);

		Long vectorId = vectorDataService.generateAndSaveRandomVector();

		assertNotNull(vectorId);
	}
}
