#! /bin/sh 

export PATH=/bin:/usr/bin:/sbin:$HOME/shell:$PATH
cd $(dirname $0)

shutdownDatabase() {
    if [ -n "$dbstarted"  ]
    then
	echo "stopping DB"
	cd ~/local-mysql-docker
	./stopContainer.sh
    fi
}

trap "shutdownDatabase; exit 1" INT

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
docker run --add-host quoteDBServer:$ip --add-host batchDBServer:$ip -v ~/.blazartech:/root/.blazartech drsaaron/$imageName 2>&1 | tee /tmp/qotd-$(date +%Y-%m-%d).log

# shutdown DB, if we started it.
shutdownDatabase
