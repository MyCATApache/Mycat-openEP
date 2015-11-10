#!/bin/sh

cdir=$(cd "$(dirname "$0")"; pwd)
#echo ${cdir}
cd ${cdir}/..

#echo `pwd`



usage()
{
        echo "usage: `basename $0` start|stop nodename"
	echo "       must exist configfile named node_nodename in dir config"

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

		if [ ! -d "./db/$PROCESS"  ] ;then
			mkdir -p ./db/$PROCESS
		fi
		if [ ! -f "./pid/node_$PROCESS" ];then
			touch ./pid/node_$PROCESS
		fi

		if [ -f ./config/node_$PROCESS.conf  ]; then
		
			m_pid=$(cat ./pid/node_$PROCESS)

			echo "$m_pid"
			if [  "$m_pid"x != ""x   ];then
				echo "Already running,PID:$m_pid"
			else	
				icegridnode --Ice.Config=./config/node_$PROCESS.conf --nochdir --daemon --pidfile ./pid/node_$PROCESS
			fi
		else

		 usage

		fi	


		
        ;;
        stop|Stop) echo "Stopping..$PROCESS"
        # some process to go here
	  	
		
                if [ -f ./pid/node_$PROCESS ]; then
		
			m_pid=$(cat ./pid/node_$PROCESS)

                        echo "$m_pid"
                        if [  "$m_pid"x != ""x   ];then
				kill $m_pid
				:>./pid/node_$PROCESS
			else
				echo "not running..."	
			fi

		else
		  echo "not find pidfile"
		fi	   	


        ;;
        *)usage
        ;;
esac


