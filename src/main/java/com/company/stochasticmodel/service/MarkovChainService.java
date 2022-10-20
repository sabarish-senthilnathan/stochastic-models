package com.company.stochasticmodel.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.stochasticmodel.handler.MarkovChainHandler;

@Service
public class MarkovChainService {
	@Inject
	private MarkovChainHandler markovChainHandler;
	
	public MarkovChainService(MarkovChainHandler markowChainHandler) {
		this.markovChainHandler = markowChainHandler;
	}
	
	public String generateText(String input, int prefix, int suffix, int wordCount) {
		return markovChainHandler.generateText(input, prefix,  suffix, wordCount);
	}
}
