#! /bin/sh 

export PATH=/bin:/usr/bin:/sbin:$HOME/shell:$PATH
cd $(dirname $0)

running=1

shutdownDatabase() {
    if [ -n "$dbstarted"  ]
    then
	echo "stopping DB"
	cd ~/local-mysql-docker
	./stopContainer.sh
    fi
}

exitHandler() {
    shutdownDatabase
    exit $running
}

# shutdown the database server on an interrupt or completion.
trap "exitHandler" INT EXIT

# start the database if it's not already running
if ! docker ps | grep -q mysql1
then
    echo "starting DB..."
    docker start mysql1
    dbstarted=true
    sleep 10
fi

# run the job
echo "running job"
imageName=$(dockerImageName.sh)
docker run --user $(id -u):$(id -g) --network qotd -v ~/.blazartech:/home/$(whoami)/.blazartech $imageName 2>&1 | tee /tmp/qotd-$(date +%Y-%m-%d).log

# done running
running=0
