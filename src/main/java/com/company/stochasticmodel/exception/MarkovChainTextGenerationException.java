package com.company.stochasticmodel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MarkovChainTextGenerationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MarkovChainTextGenerationException(String message) {
        super(message);
    }
}
