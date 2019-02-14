#! /bin/ksh

export PATH=/bin:/usr/bin:$PATH
docker run -v ~/.blazartech:/root/.blazartech drsaaron/qotdjob 2>&1 | tee /tmp/qotd-$(date +%Y-%m-%d).log

