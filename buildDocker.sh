#! /bin/ksh

version=$(grep '^ *<version>' pom.xml | head -1 | perl -pe 's%</?version>%%g; s/-SNAPSHOT//; s/^ *//; s/\W*$//g')

docker build -t drsaaron/qotdjob .
docker tag drsaaron/qotdjob drsaaron/qotdjob:$version
