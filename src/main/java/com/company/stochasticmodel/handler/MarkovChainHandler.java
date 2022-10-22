package com.company.stochasticmodel.handler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.company.stochasticmodel.exception.MarkovChainTextGenerationException;

@Component
public class MarkovChainHandler {
	public Map<String, List<String>> trainDataset(String input, int prefix, int suffix) {
		String[] words = input.split("[\\s]+");
		Map<String, List<String>> modelMap = new HashMap<>();

		for (int i = 0; i < words.length - prefix; i = i + prefix) {
			List<String> word = new LinkedList<String>();
			int j = 0;
			int k = i;
			
			while(j < prefix) {
				word.add(words[k]);
				k++;
				j++;
			}
			
			String key = String.join(" ", word);
			List<String> value = new LinkedList<String>();
			int l = 0;
			
			while(l < suffix) {
				value.add(words[(k - 1) + 1 + l]);
				l++;
			}
			
			if (modelMap.containsKey(key)) {
				List<String> suffixes = modelMap.get(key);		
				suffixes.add(String.join(" ", value));
				modelMap.put(key, suffixes);
			} else {
				List<String> suffixes = new LinkedList<String>();			
				suffixes.add(String.join(" ", value));
				modelMap.put(key, suffixes);
			}
		}

		return modelMap;
	}

	public String generateText(String input, int prefix, int suffix, int wordCount) {
		Map<String, List<String>> modelMap = trainDataset(input, prefix, suffix);
		List<String> keys = new LinkedList<>(modelMap.keySet());
		List<String> generatedText = new LinkedList<>();
		wordCount -= prefix;
		wordCount -= suffix;

		try {
			Random random = new Random();
			int start = random.nextInt(keys.size());
			String startIndexKey = keys.get(start);
			generatedText.add(startIndexKey);
			
			List<String> suffixes = modelMap.get(startIndexKey);
			String startValue = suffixes.get(new Random().nextInt(suffixes.size()));
			generatedText.add(startValue);
			wordCount /= suffix;
			
			while (wordCount != 0) {
				String nextKey = startValue;
				suffixes = modelMap.get(nextKey);
				
				if (suffixes == null) {
					String partialKey = keys.stream().filter(key -> key.contains(nextKey)).findFirst().orElse(null);
					suffixes = modelMap.get(partialKey);
				}
				
				if (suffixes == null || suffixes.size() == 0)
					return String.join(" ", generatedText);

				String wordChoice = suffixes.get(new Random().nextInt(suffixes.size()));
				startValue = wordChoice;
				generatedText.add(wordChoice);
				wordCount--;
			}
		} catch (Exception ex) {
			throw new MarkovChainTextGenerationException("Error generating text. " + ex.getMessage());
		}

		return String.join(" ", generatedText);
	}
}
