#!/bin/sh

in=$1
cn=$2
ldir=$3

if [ $# -eq 2 ]
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
    ${in} /usr/bin/supervisord
elif [ $# -ge 3 ]
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
    -v ${ldir}:/opt/www/openep \
    ${in} /usr/bin/supervisord
else
    #statements
    echo "usage:    run-php.sh [imageName] [containerName] [hostWebRoot]"
    echo "说明:"
    echo "imageName           docker镜像ID"
    echo "containerName     启动的容器名称"
    echo "hostWebRoot         宿主机上项目代码 'Mycat-openEP/shop-web/php/' 目录的绝对路径"
fi