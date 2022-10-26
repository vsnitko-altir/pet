Project with a bunch of technologies to demonstrate how they work. 
For now the main purpose was to demonstrate Elasticsearch and Redux Toolkit.
Going to be expanded.

### Other technologies used

* Backend
    * Java 19, Spring Boot 3
    * H2 database
    * Elasticsearch 7.17.6
    * Spring Security
* Frontend
    * React 18 (TypeScript based)
    * Redux Toolkit
    * Chakra UI

### How to run

Run Elasticsearch
#### `docker run -d --name es7176 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.17.6`

Run Java 19 (server will run on port 8081)
#### `mvn clean install`
#### `java -jar target/pet-demo.jar`

Run React (server will run on port 3001)
#### `cd react`
#### `npm start`

### Main page

![Screenshot_3](https://user-images.githubusercontent.com/54511054/197914563-48e54ccf-1730-49ef-88a8-8fee67e9c87a.png)

### Elasticsearch usage

Elasticsearch used here to find users by firstName and lastName.
Here are some rules of searching: 
* We can enter 1 or 2 words. We will get search results with firstName or/and lastName starting with each entered word. Entered words after 2nd will be ignored
* Exact matches will have higher score and will be placed at the beginning of results
* We can flip lastName and firstName in query, but results will have lower score and will be placed at the end of results

E.g. if we have users `[Maria Kennedy-Johnson, John Johnson, Jane Kennedy]` then <br/>
query `jOhN` will return `[John Johnson, Maria Kennedy-Johnson]` in order<br/>
query `j k` will return `[Jane Kennedy]`

### Redux usage

Used to store signed-in user



