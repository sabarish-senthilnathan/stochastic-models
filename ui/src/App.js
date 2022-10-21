import './App.css';
import { useState } from 'react';

function App() {
	const [prefix, setPrefix] = useState("");
	const [suffix, setSuffix] = useState("");
	const [wordCount, setWordCount] = useState("");
	const [fileSelectText, setFileSelectText] = useState("Select your file!");
	const [selectedFile, setSelectedFile] = useState();
	const [generatedText, setGeneratedText] = useState("");

	const changeFileHandler = (event) => {
		setSelectedFile(event.target.files[0]);
		setFileSelectText(event.target.files[0].name);
	};
	
	const submitForm = (event) => {
		event.preventDefault()
		const formData = new FormData();
		formData.append('wordCount', wordCount);
		formData.append('prefix', prefix);	
		formData.append('suffix', suffix);		
		formData.append('file', selectedFile);

		fetch('http://localhost:8080/v1/api/markov/text-generation', {
	    	method: 'POST',
	    	body: formData,
	    })
	    .then(response => {
	         if (response.ok) {  
	             return response.text();
	         } 
	         else {  
	             return response.json().message;
	        }
    	})
		.then(text => setGeneratedText(text))
		.catch((error) => {
			// show a modal with the error message. 
			alert(error);
			console.error('Error:', error);
		});
	}; 
	
    return (
		<div class="markov-chain ">
	        <div class="container">
	            <div class="intro">
	                <h2 class="text-center">Markov Chain Text Generator</h2>
	                <p class="text-center">
		                A simple tool for text generation using Markov chains. 
		                The prefix word count, and the suffix word count is used for training the data set. 
		                Choose a .txt file to upload and the tool generates random text based on the text uploaded.
	                </p>
	            </div>
	            <form class="form-inline" onSubmit={submitForm}>
	                <div class="form-group">
 						<label for="prefix">Prefix length <span class="optional">(optional defaults to 1)</span></label>
	                	<input class="form-control" type="text" name="prefix" value={prefix} onChange={(e) => setPrefix(e.target.value)} placeholder="Enter Prefix length" />
	            	</div>
	             	<div class="form-group">
 						<label for="suffix">Suffix length <span class="optional">(optional defaults to 1)</span></label>
	                	<input class="form-control" type="text" name="prefix" value={suffix} onChange={(e) => setSuffix(e.target.value)} placeholder="Enter suffix length" />
	            	</div>
	            	<div class="form-group">
 						<label for="count">Word count <span class="optional">(optional defaults to 50)</span></label>
	                	<input class="form-control" type="text" name="prefix" value={wordCount} onChange={(e) => setWordCount(e.target.value)} placeholder="Enter word count for the generated text" />
	            	</div>
					<label for="file">Input file <span class="required">*</span></label>
					<div class="file-upload-wrapper" data-text={fileSelectText}>

	      				<input name="file-upload-field" type="file" class="file-upload-field" onChange={changeFileHandler} value="" />
	    			</div>
	                <button class="btn btn-primary" type="submit">Generate Text </button>
	            </form>       
	        </div>
	        <textarea id="generated-text-area" name="textarea" value={generatedText}>
	        </textarea>
	    </div>
  	); 
}

export default App;
