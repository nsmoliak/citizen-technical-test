# citizen-technical-test
Citizen API Technical test

## The purpose of task:
* receives the provided data 
* returns the data as JSON
* provides simple sorting and/or filtering options for the output data
* grouping of residents with the same address
* demonstrates suitable tests in the key areas required
* normalise and complete all the missing data fields for all persons & addresses
* group the persons who live at the same address

## Ready

* Receive data as csv and receive data as JSON
* Sorting by selected parameter
* Filtering by selected parameter
* Grouping by selected parameter
* Normalization

#### Normalization

There was no directory or api from where to get the data, so possible omissions were substituted according to the next principles.
  * Filling in an empty state or country code occurs when the data contains the same city with the specified state and country code.
  * Filling in the second address occurs if there is a connection in the postcodes or the first and second addresses.
  * Filling in the last name occurs for people with the same address.
  
### Maven

https://maven.apache.org/download.cgi

### Cloning app
1. `git clone https://github.com/nsmoliak/citizen-technical-test.git`
2. `cd citizen-technical-test`

## Running app

### App via maven

1. `./mvnw.cmd clean install`
2. `./mvnw spring-boot:run`

### Run app manually

## Application usage

After launch, you can check if the application has started by clicking on the link in the [Actuator](http://localhost:8080/api/actuator/health) or follow the link for the [Swagger](http://localhost:8080/api/swagger-ui/#/).

## Example

1. An example of a request with a csv file:
   `curl --location --request POST 'http://localhost:8080/api/persons/file' \
   --form 'file={test_file}'`

Example of test_file you can find in examples dir.

2. An example of a request with a JSON data:
   `curl --location --request POST 'http://localhost:8080/api/persons' \
   --header 'Content-Type: application/json' \
   --data-raw '{test_data_raw}'`

Example of test_data_raw you can find in examples dir.

3. An example of a request with a csv file with all parameters:
`curl --location --request POST 'http://localhost:8080/api/persons/file?sort=firstname,ASC&group=countryCode&normalize=true' \
--form 'file={test_file}'`

Example of test_file you can find in examples dir.

4. An example of a request with a JSON data with all parameters:
    `curl --location --request POST 'http://localhost:8080/api/persons?sort=firstname,ASC&group=countryCode&normalize=true&filter=city,London' \
   --header 'Content-Type: application/json' \
   --data-raw '{test_data_raw}'`

Example of test_data_raw you can find in examples dir.