FROM drsaaron/blazarjavabase:1.0

# add the target directory, which has the jars
ADD ./target ./target

# add a shell script to run the java program
ADD ./runJob.sh ./runJob.sh

# add CA certs
ADD ./tmp ./tmp

# run the script
CMD ./runJob.sh
