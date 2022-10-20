Text Generator Using Markov Chains 
======================================	

This Spring Boot project merged with a React app allows users to generate random text using Markow chains. The Spring Boot application has REST endpoints that generates random text for a given text file.

## Prerequisites
* 

## Running the Full Application
#### Running the backend:

To build the package as a single artifact by running the `./mvnw clean install`. 

To start the Spring Boot application using Maven:
```bash
$ cd stochasticmodel
$  ../mvnw spring-boot:run
```

You can access the application at `http://localhost:8080`.
#### Running the frontend:
```
$ cd stochasticmodel/ui
$ npm start
```
You can access the frontend React application `http://localhost:3000/`.

![My Image](/images/react-app.png)

## REST APIs
The Spring Boot applications defines following APIs.

* `POST /v1/api/markov/text-generation`

```
curl -X POST -F 'wordCount=50' -F 'prefix=1' -F 'suffix=1' -F 'file=@/path/to/file.txt' http://localhost:8080/v1/api/markov/text-generation
```



