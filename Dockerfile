# Start from alpine image
FROM alpine

# setup timezone
RUN apk add tzdata
ENV TZ=US/Central
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# install java
RUN apk add openjdk11-jdk

# this is the prod environment
ENV ENVIRONMENT prod

# set a working directory
WORKDIR /app

# add the target directory, which has the jars
ADD ./target ./target

# add a shell script to run the java program
ADD ./runJob.sh ./runJob.sh

# add CA certs
ADD ./tmp ./tmp

# run the script
CMD ./runJob.sh
