#!/bin/bash

ver='7.0.62'
pkg="apache-tomcat-${ver}.tar.gz"
dest='/opt/javahome'

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget http://dl.mycat.io/${pkg}

mkdir -p ${dest}
tar zxf ${pkg} -C ${dest}
cp -a tomcat-tpl ${dest}
cd ${dest}
ln -s apache-tomcat-${ver} tomcat
mkdir ../tomcat
cp -a tomcat-tpl/* ../tomcat
chmod 777 ../tomcat/bin/run.sh

cat > /etc/supervisord.d/tomcat.conf << EOF
[program:tomcat]
command=/opt/tomcat/bin/run.sh
priority=1500
stopsignal=INT
stopasgroup=true
autorestart=true
EOF
