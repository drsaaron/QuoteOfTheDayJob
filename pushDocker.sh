#! /bin/sh

version=$(getPomAttribute.sh version | sed -e 's/-SNAPSHOT//')

docker push drsaaron/qotdjob:$version
docker tag drsaaron/qotdjob:$version  drsaaron/qotdjob:latest
docker push drsaaron/qotdjob:latest
