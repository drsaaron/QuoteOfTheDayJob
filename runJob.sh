#! /bin/sh

appEnv=${ENVIRONMENT:-test}

artifact=$(getPomAttribute.sh artifactId)
version=$(getPomAttribute.sh version)

# certificates
certDir=tmp/certs
truststore=$certDir/rds-truststore.jks
storepassword=changeit

java -Djavax.net.ssl.trustStore=$trustStore -Djavax.net.ssl.trustStorePassword=$storepassword -jar target/$artifact-$version.jar $(date +%Y-%m-%d) --spring.config.name=application,$appEnv
