#!/bin/sh

cdir=$(cd "$(dirname "$0")"; pwd)
#echo ${cdir}
cd ${cdir}/..

echo `pwd`



usage()
{
        echo "usage: `basename $0` start|stop master|slave"
}       

OPT=$1
PROCESS=$2

if [ $# -ne 2 ]; then
        usage
        exit 1
fi      
case $OPT in
        start|Start) echo "Starting..$PROCESS"
        # some process to go here

		if [ ! -d "./db/$PROCESS" ]; then
			mkdir -p ./db/$PROCESS
		fi			
		if [ ! -f "./pid/registry_$PROCESS" ];then
			touch ./pid/registry_$PROCESS
		fi	

		if [ "$PROCESS" = "master" ] || [ "$PROCESS" = "slave" ]; then
		
			m_pid=$(cat ./pid/registry_$PROCESS)

			echo "$m_pid"
			if [  "$m_pid"x != ""x   ];then
				echo "Already running,PID:$m_pid"
			else	
				icegridregistry --Ice.Config=./config/registry_$PROCESS.conf --nochdir --daemon --pidfile ./pid/registry_$PROCESS
			fi
		else
		 usage

		fi	


		
        ;;
        stop|Stop) echo "Stopping..$PROCESS"
        # some process to go here
	  	
		
                if [ "$PROCESS" = "master" ] || [ "$PROCESS" = "slave" ]; then
		
			m_pid=$(cat ./pid/registry_$PROCESS)

                        echo "$m_pid"
                        if [  "$m_pid"x != ""x   ];then
				kill $m_pid
				:>./pid/registry_$PROCESS
			else
				echo "not running..."	
			fi

		else
		  usage
		fi	   	


        ;;
        *)usage
        ;;
esac


