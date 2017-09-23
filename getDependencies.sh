#! /bin/sh

mvn dependency:copy-dependencies

# remove some duplicates
for file in spring-beans-4.0.5.RELEASE.jar spring-core-4.0.5.RELEASE.jar spring-aop-4.0.5.RELEASE.jar slf4j-api-1.8.0-alpha2.jar slf4j-simple-1.8.0-alpha2.jar
do
    rm -f target/dependency/$file
done

