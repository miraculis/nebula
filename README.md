# Pre-requisites:
1. Install JDK 8
2. Install and run last Kafka
3. Install and run last Cassandra
4. Install last gradle

# Building:
1. Clone repository to your local machine
2. Make sure you have local maven repository (~/.m2 folder)
3. Build pojos sub-project first by `gradle assemble`
4. Install pojos into local maven repo by `gradle publishToMavenLocal`
5. Build other services by running `gradle assemble` in each service folder
6. Insert products into cassandra by running `java -jar build/libs/cassandra.jar Product.csv ` in cassandra service root folder.

Now you are ready to spam messages to entry point - product-service

#Running services:
1. Run built product-service by `java -jar products-service-0.1.0.jar` (in product-service/build/lib folder)
2. Run built wc-service by `java -jar wc-service-0.1.0.jar` (in wordscheck-service/build/lib folder)
3. Run built ntf-service by `java -jar ntf-service-0.1.0.jar` (in notifications-service/build/lib folder)

#CURLing:
Since application is designed in kinda-clustered way, make sure you submit correct productId. Not integer, but UUID from nebula.products columns family.

Enjoy.
