#! /bin/sh

version=$(getPomAttribute.sh version | sed -e 's/-SNAPSHOT//')

docker build -t drsaaron/qotdjob .
docker tag drsaaron/qotdjob drsaaron/qotdjob:$version
