### How to set up? ###
* Git link to clone
	 https://github.com/SindhuGanapathi/tradereportingengine/


* application properties file
     application.properties


* Technical stack 
	 Java 8
	 Spring Boot 2.7.0
	 Maven 4.0.0


* Database configuration 
	in-memory h2 database


* API Endpoints 
	GET - http://localhost:8081/v1/tradeEvents/getFilteredData
	

* Approach
	Store the static trade events xml file data into h2 database 
	Expose Rest API using SpringBoot Rest Controller, read the trade events data from h2 database


* Improvement
    Java reactive programming
	Dynamic filtering in SpringBoot