package com.statistics.vector.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.statistics.vector.controller.VectorController;
import com.statistics.vector.exceptionHandler.VectorDataExceptionHandler;
import com.statistics.vector.service.VectorDataService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
@WebMvcTest(VectorController.class)
public class vectorControllerTest {

	private MockMvc mockMvc;

	@Mock
	private VectorDataService vectorDataService;

	@InjectMocks
	private VectorController vectorController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.vectorController)
				.setControllerAdvice(new VectorDataExceptionHandler()).build();
	}

	@Test
	public void testGetStatistics() throws Exception {

		Map<String, Double> statsMap = new HashMap<>();
		statsMap.put("mean", new Double(10));
		statsMap.put("standardDeviation", new Double(8.8789));
		when(this.vectorDataService.calculateStatistics(ArgumentMatchers.anyLong())).thenReturn(statsMap);

		this.mockMvc.perform(get("/vector/1/statistics").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testGetStatisticsBadRequest() throws Exception {

		this.mockMvc.perform(get("/vector//statistics").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testGenerateAndSaveVector() throws Exception {

		when(this.vectorDataService.generateAndSaveRandomVector()).thenReturn(1L);

		mockMvc.perform(MockMvcRequestBuilders.post("/vector/generate")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("1"));
	}
}
