#! /bin/ksh

version=$(grep '^ *<version>' pom.xml | head -1 | perl -pe 's%</?version>%%g; s/-SNAPSHOT//; s/^ *//; s/\W*$//g')

docker push drsaaron/qotdjob:$version
docker tag drsaaron/qotdjob:$version  drsaaron/qotdjob:latest
docker push drsaaron/qotdjob:latest
