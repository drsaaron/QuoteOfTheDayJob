#! /bin/sh

~/netbeans/netbeans-8.2/java/maven/bin/mvn dependency:copy-dependencies

# remove some duplicates
for file in spring-beans-4.0.5.RELEASE.jar spring-core-4.0.5.RELEASE.jar spring-aop-4.0.5.RELEASE.jar
do
    rm -f target/dependency/$file
done

