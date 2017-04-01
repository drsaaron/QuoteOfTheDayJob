# Start from the java docker.
FROM openjdk:latest

# add the target directory, which has the jars
ADD ./target /app/target

# add the crypto file data, which for some reason can't be read from ~/.blazartech
#ADD ./bt /root/.blazartech

# add a shell script to run the java program
ADD ./runJob-docker.sh /app/runJob-docker.sh

# run the script
CMD /app/runJob-docker.sh
