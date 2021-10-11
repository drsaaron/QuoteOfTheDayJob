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
if ! docker ps | grep mysql1
then
    echo "starting DB..."
    docker start mysql1
    dbstarted=true
    sleep 10
fi

# get the IP address of the host
ip=$(ifconfig wlo1 | grep inet | awk '$1=="inet" {print $2}')
#ip=$(nslookup blazartech-test.csl2otan97lp.us-east-2.rds.amazonaws.com | grep Address| tail -1 | awk '{ print $2 }')

# run the job
echo "running job"
imageName=$(dockerImageName.sh)
docker run --add-host quoteDBServer:$ip --add-host batchDBServer:$ip -v ~/.blazartech:/root/.blazartech $imageName 2>&1 | tee /tmp/qotd-$(date +%Y-%m-%d).log

# done running
running=0
