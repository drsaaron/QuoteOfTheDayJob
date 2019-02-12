#! /bin/ksh

export PATH=/bin:/usr/bin:$PATH
docker run -v ~/.blazartech:/root/.blazartech drsaaron/qotdjob | tee /tmp/qotd-$(date +%Y-%m-%d).log

