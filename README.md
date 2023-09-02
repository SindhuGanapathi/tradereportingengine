### Trade Reporting Engine ###
Trade Reporting Engine
A JAVA SpringBoot program that reads a set of Trade Events XML files, extracts certain fields and stores them in database.
REST Controller has the HTTP request configured that reports the filtered trade events in JSON Format.

Project Implemetation
Trade Reporting Engine functionality is achieved by the following steps and resources.
The provided static Trade Events XML files are placed in the application under the path: src/main/resources/eventXML
At the application startup, these XML events are parsed and stored in the h2 database, h2 is used for persistent storage.
Rest Controller configures the HTTP request/response inorder to fetch the filtered list of trade events as per the requirement 
from H2 database. Criterias/Requirements provided implemented using Java Streams and filter operations are as follows:
1. (The seller_party is EMU_BANK and the premium_currency is AUD) or (the seller_party is BISON_BANK and the premium_currency is USD)
2.	The seller_party and buyer_party must not be anagrams 
Only events that match the above listed criteria should be reported when the REST End point http://localhost:8081/v1/tradeEvents/getFilteredData
is trigerred.

### How to set up? ###
* Git link
	 https://github.com/SindhuGanapathi/tradereportingengine/tree/main


* application properties file
     application.properties


* Technical stack 
	 Java 8
	 Spring Boot
	 Maven


* Database configuration 
	in-memory h2 database


* API Endpoints 

	GET - http://localhost:8081/v1/tradeEvents/getFilteredData
	

* Approach
	Store the static trade events xml file data into h2 database 
	Expose Rest API using SpringBoot Rest Controller


* Improvements
  Java reactive programming
  Dynamic filtering in SpringBoot
