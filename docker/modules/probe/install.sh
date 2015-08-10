#!/bin/bash

ver='2.3.3'
pkg="probe-${ver}.zip"
dest='/opt/javahome/probe'

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget http://dl.mycat.io/${pkg}

unzip -qq ${pkg} -d ${dest}
cd ${dest}
unzip -qq probe.war
rm probe.war

cat > /opt/tomcat/conf/Catalina/localhost/probe.xml << EOF
<?xml version='1.0' encoding='utf-8'?>
<Context path="/probe" privileged="true" docBase="/opt/javahome/probe" reloadable="false"/>
EOF
