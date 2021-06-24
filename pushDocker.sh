#! /bin/sh

version=$(getPomAttribute.sh version | sed -E -e 's/-[A-Z]+$//')

docker push drsaaron/qotdjob:$version
docker tag drsaaron/qotdjob:$version  drsaaron/qotdjob:latest
docker push drsaaron/qotdjob:latest
