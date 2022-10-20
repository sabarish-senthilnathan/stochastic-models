package com.company.stochasticmodel.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileNotSupportedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public FileNotSupportedException(String message) {
        super(message);
    }
}