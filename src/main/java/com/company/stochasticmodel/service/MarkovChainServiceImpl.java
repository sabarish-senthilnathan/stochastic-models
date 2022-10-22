package com.company.stochasticmodel.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.stochasticmodel.handler.MarkovChainHandler;

@Service
public class MarkovChainServiceImpl implements MarkovChainService {
	@Inject
	private MarkovChainHandler markovChainHandler;
	
	public MarkovChainServiceImpl(MarkovChainHandler markovChainHandler) {
		this.markovChainHandler = markovChainHandler;
	}
	
	@Override
	public String generateText(String input, int prefix, int suffix, int wordCount) {
		return markovChainHandler.generateText(input, prefix,  suffix, wordCount);
	}
}
