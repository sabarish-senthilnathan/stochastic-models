package com.company.stochasticmodel.service;

import org.springframework.stereotype.Service;

@Service
public interface MarkovChainService {
	public String generateText(String input, int prefix, int suffix, int wordCount);
}
