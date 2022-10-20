package com.company.stochasticmodel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.company.stochasticmodel.controller.MarkovChainController;
import com.company.stochasticmodel.exception.FileNotSupportedException;
import com.company.stochasticmodel.exception.MarkovChainTextGenerationException;
import com.company.stochasticmodel.service.MarkovChainService;

@ExtendWith(MockitoExtension.class)
public class MarkovControllerTest {
	@InjectMocks
	MarkovChainController markovController;

	@Mock
	MarkovChainService markovChainService;

	@Mock
	MultipartFile file1;

	@Test
	public void generateText_WhenTextPlainFileUpload_200OK() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

		when(markovChainService.generateText(anyString(), anyInt(), anyInt(), anyInt())).thenReturn("");
		ResponseEntity<String> responseEntity = markovController.generateText(file, 1, 1, 1);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void generateText_WhenFileNotPlainText_ThrowFileNotSupportedException() throws IOException {
		MockMultipartFile file = new MockMultipartFile("data", "filename.jpg", "image/jpg", "some jpg".getBytes());

		FileNotSupportedException thrown = assertThrows(FileNotSupportedException.class,
				() -> markovController.generateText(file, 1, 1, 1), "Not supported file.");

		assertTrue(thrown.getMessage().equals("Not supported file."));
	}
	
	@Test
	public void generateText_WhenProcessingError_ThrowMarkovChainTextGenerationException() throws IOException {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some text".getBytes());
		when(markovChainService.generateText(anyString(), anyInt(), anyInt(), anyInt())).thenThrow(MarkovChainTextGenerationException.class);

		MarkovChainTextGenerationException thrown = assertThrows(MarkovChainTextGenerationException.class,
				() -> markovController.generateText(file, 1, 1, 1), "Error generating text using markov chain.");

		assertTrue(thrown.getMessage().equals("Error generating text using markov chain."));
	}
}
