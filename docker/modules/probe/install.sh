#!/bin/bash

ver='2.3.3'
pkg="probe-${ver}.zip"
dest='/opt/javahome/probe'

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget https://github.com/psi-probe/psi-probe/releases/download/${ver}/${pkg}

unzip ${pkg} -d ${dest}
cd ${dest}
unzip probe.war
rm probe.war

cat > /opt/tomcat/conf/Catalina/localhost/probe.xml << EOF
<?xml version='1.0' encoding='utf-8'?>
<Context path="/probe" privileged="true" docBase="/opt/javahome/probe" reloadable="false"/>
EOF
