#! /bin/ksh

export PATH=/bin:/usr/bin:/sbin:$PATH

# start the database
echo "starting database"
dbps=$(docker ps | grep mysql1)
if [ "$dbps" = "" ]
then
    echo "starting DB..."
    docker start mysql1
    sleep 10
    dbstarted=true
fi

# get the IP address of the host
ip=$(ifconfig wlo1 | grep inet | awk '$1=="inet" {print $2}')
#ip=$(nslookup blazartech-test.csl2otan97lp.us-east-2.rds.amazonaws.com | grep Address| tail -1 | awk '{ print $2 }')

# run the job
echo "running job"
docker run --add-host quoteDBServer:$ip --add-host batchDBServer:$ip -v ~/.blazartech:/root/.blazartech drsaaron/qotdjob 2>&1 | tee /tmp/qotd-$(date +%Y-%m-%d).log

# stop the database
# shutdown DB, if we started it.
if [ "$dbstarted" = "true" ]
then
    echo "stopping DB"
    cd ~/local-mysql-docker
    ./stopContainer.sh
fi
