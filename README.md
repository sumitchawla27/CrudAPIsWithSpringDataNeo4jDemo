Steps to test and bring the project up and running
1. Clone the project from git repo link
2. After installation change the default password of neo4j server and mention that password in application.properties file  
3. Make sure Neo4j server is up and running on default port 7687 with default settings
4. ./mvnw spring-boot:run command can be used to bring the project up.

Other Key points:
you can build the JAR file with ./mvnw clean package and then run the JAR file, as follows:

java -jar target/crudapiswithneo4j-0.0.1-SNAPSHOT.jar  