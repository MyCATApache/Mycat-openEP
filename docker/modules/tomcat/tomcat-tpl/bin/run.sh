#!/bin/sh

cd `dirname $0`/../

[ -z "$JAVA_HOME" ] && export JAVA_HOME=/opt/javahome/jdk
export CATALINA_HOME=/opt/javahome/tomcat
export CATALINA_BASE=`pwd`

${CATALINA_HOME}/bin/catalina.sh run