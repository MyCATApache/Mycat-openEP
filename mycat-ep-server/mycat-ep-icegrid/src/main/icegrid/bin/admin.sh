#!/bin/sh

cdir=$(cd "$(dirname "$0")"; pwd)
#echo ${cdir}
cd ${cdir}/..

echo `pwd`

icegridadmin --Ice.Config=./config/admin.conf

