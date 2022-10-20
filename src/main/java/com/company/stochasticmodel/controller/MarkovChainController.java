package com.company.stochasticmodel.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.stochasticmodel.exception.FileNotSupportedException;
import com.company.stochasticmodel.exception.MarkovChainTextGenerationException;
import com.company.stochasticmodel.service.MarkovChainService;

@RestController
@CrossOrigin
@RequestMapping("/v1/api/markov")
public class MarkovChainController {
	private MarkovChainService markowChainService;
	
	public MarkovChainController(MarkovChainService markowChainService) {
		this.markowChainService = markowChainService;
	}
	
	/**
	 * Returns a String object representing random text generated using Markov chains. 
	 *s
	 * 
	 * @param file      a .txt file containing some text
	 * @param prefix    the length of words to be used as prefix
	 * @param suffix    the length of words to be used as suffix 
	 * @param wordCount the length of the random text that needs to be generated
	 * @return          generated text
	 */
    @RequestMapping(value = "/text-generation", method = RequestMethod.POST)
	public ResponseEntity<String>  generateText(@RequestParam("file") MultipartFile file,
			@RequestParam(name = "prefix", defaultValue = "1") int prefix,
			@RequestParam(name = "suffix", defaultValue = "1") int suffix,
			@RequestParam(name = "wordCount", defaultValue = "50") int wordCount) {
		String generatedText = "";
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (file.isEmpty() || !extension.equals("txt")) {
	        throw new FileNotSupportedException("Not supported file.");
	    }
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String fileData = new String(bytes);
				generatedText  = markowChainService.generateText(fileData, prefix, suffix, wordCount);
			} catch (Exception ex) {
				throw new MarkovChainTextGenerationException("Error generating text using markov chain."); 
			}
		}
		
	    return new ResponseEntity<>(generatedText, HttpStatus.OK);
	}
}
