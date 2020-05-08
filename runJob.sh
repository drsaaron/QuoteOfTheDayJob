#! /bin/sh

appEnv=${ENVIRONMENT:-test}

# certificates
certDir=tmp/certs
truststore=$certDir/rds-truststore.jks
storepassword=changeit

java -Djavax.net.ssl.trustStore=$trustStore -Djavax.net.ssl.trustStorePassword=$storepassword -jar target/QuoteOfTheDayJob-1.*-SNAPSHOT.jar $(date +%Y-%m-%d) --spring.config.name=application,$appEnv
