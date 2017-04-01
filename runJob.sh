#! /bin/ksh

#jarList=$(find ./target/dependency -name '*.jar' -print)
#export CLASSPATH=$(echo $jarList | sed 's/ /:/g'):$CLASSPATH

export CLASSPATH=$(echo ./target/*.jar ./target/dependency/*.jar | sed 's/ /:/g')
echo $CLASSPATH

java com.nm.ffba.common.batch.main.JobRunner SpringXMLConfig.xml dailyQuoteOfTheDayDistributionJob $(date +%Y-%m-%d)
