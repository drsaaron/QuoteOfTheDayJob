#! /bin/sh

# $Id$
# $Log$
#***************************************************************************
# wrapper script for cron to run to run the posting job.
#***************************************************************************

export PATH=/bin:/usr/bin:$PATH
cd ~/BlazarTech/QuoteOfTheDayJob
./runJob.sh > /tmp/qotd-$(date +%Y-%m-%d).log 2>&1
