#!/bin/sh

in=$1
cn=$2

if [ $# -ge 2 ]
then
    [ ! -d /opt/docker/store1 ] && mkdir -p /opt/docker/store1
    docker run --privileged -d -p 2222 -p 80 -p 8080 -p 2181 -p 3306 -p 8066 -p 9066 -p 4061 -p 8888 -p 9001 -h ${cn} --name ${cn} -v /opt/data-vol -v /opt/docker/store1:/opt/data-ext ${in} /usr/bin/supervisord
else
    echo "run.sh [imageName] [containerName]"
fi
