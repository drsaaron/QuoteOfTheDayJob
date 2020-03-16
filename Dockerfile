# Start from the java docker.
FROM openjdk:latest

# set a working directory
WORKDIR /app

# add the target directory, which has the jars
ADD ./target ./target

# add a shell script to run the java program
ADD ./runJob-docker.sh ./runJob-docker.sh

# add CA certs
ADD ./tmp ./tmp

# run the script
CMD ./runJob-docker.sh
