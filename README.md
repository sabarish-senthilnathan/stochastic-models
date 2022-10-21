Text Generator Using Markov Chains 
======================================	

This Spring Boot project merged with a React application allows users to generate random text using Markow chains. The repositoy consists of two modules:
* A Spring Boot back-end application consists of REST endpoints for generating random text for a given text file.
* A React front-end appplication (under the `ui` folder in the root project folder) that lets users upload data sets and preview the random text generated.

## Prerequisites
* Java 1.8 or higher
* Node 16.18.0 or higher
* npm 8.19.2 or higher
* Maven 3.8.6 or higher
 
## Running the Full Application
#### Running the backend:

To build the package as a single artifact by running the `./mvnw clean install`. 

To start the Spring Boot application using Maven:
```bash
$ cd stochastic-models
$  ./mvnw spring-boot:run
```

You can access the application at `http://localhost:8080`.
#### Running the frontend:
```
$ cd stochastic-models/ui
$ npm install
$ npm start
```
You can access the frontend React application `http://localhost:3000/`.

![React UI](images/react-app.png)

## REST APIs
The Spring Boot applications defines following APIs.

* `POST /v1/api/markov/text-generation`

```
curl -X POST -F 'wordCount=50' -F 'prefix=1' -F 'suffix=1' -F 'file=@/path/to/file.txt' http://localhost:8080/v1/api/markov/text-generation
```

![Demo UI](images/demo.gif)


