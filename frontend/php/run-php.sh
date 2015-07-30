#!/bin/sh

in=$1
cn=$2

if [ $# -ge 2 ]
then
    [ ! -d /opt/docker/store1 ] && mkdir -p /opt/docker/store1
    docker run --privileged -d \
    -p 2222:2222 \
    -p 8081:80 \
    -p 8082:8080 \
    -p 2181:2181 \
    -p 3366:3306 \
    -p 8066:8066 \
    -p 9066:9066 \
    -p 4061:4061 \
    -p 8888:8888 \
    -p 9001:9001 \
    -h ${cn} \
    --name ${cn} \
    -v /opt/data-vol:/opt/data-vol \
    -v /opt/docker/store1:/opt/data-ext \
    -v /home/data/opensource/Mycat-openEP/frontend/php:/opt/www/openep \
    ${in} /usr/bin/supervisord
else
    echo "run.sh [imageName] [containerName]"
fi