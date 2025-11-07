FROM drsaaron/blazarjavabase:1.59

# add the source directory and mvn stuff
ADD ./pom.xml ./pom.xml
ADD ./src ./src
ADD ./mvnw ./mvnw
ADD ./.mvn ./.mvn

# build
RUN mvnw clean install

# add a shell script to run the java program
ADD ./runJob.sh ./runJob.sh

# add CA certs
ADD ./tmp ./tmp

# run the script
CMD ./runJob.sh
