#! /bin/sh

version=$(getPomAttribute.sh version | sed -E -e 's/-[A-Z]+$//')

docker build -t drsaaron/qotdjob .
docker tag drsaaron/qotdjob drsaaron/qotdjob:$version
