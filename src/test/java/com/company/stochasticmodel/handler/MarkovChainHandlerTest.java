package com.company.stochasticmodel.handler;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MarkovChainHandlerTest {
	@InjectMocks
	MarkovChainHandler markovChainHandler;
	
	@Test
	public void trainDataset_WhenStringInput_ReturnHashMap() throws IOException {
		String dummyInput = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
		
		Map<String, List<String>> modelMap = markovChainHandler.trainDataset(dummyInput, 1, 1);

		assertTrue(modelMap.size() == 7);
		assertTrue(modelMap.containsKey("Lorem"));
		assertTrue(modelMap.get("Lorem").size() == 1);
	}
	
	@Test
	public void generateText_WhenStringInput_ReturnRandomText() throws IOException {
		String dummyInput = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
		
		String generatedText = markovChainHandler.generateText(dummyInput, 1, 1, 5);

		assertTrue(generatedText.split(" ").length > 0);
	}
}
