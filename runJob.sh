#! /bin/ksh

appEnv=test

java -jar target/QuoteOfTheDayJob-1.0-SNAPSHOT.jar $(date +%Y-%m-%d) --spring.config.name=application,$appEnv
