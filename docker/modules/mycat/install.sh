#!/bin/bash

ver='1.4-RELEASE'
pkg="Mycat-server-${ver}-openep.tar.gz"

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget https://raw.githubusercontent.com/MyCATApache/Mycat-download/master/${ver}/${pkg}

tar zxf ${pkg} -C /opt
cp run.sh /opt/mycat/bin
chmod 777 /opt/mycat/bin/run.sh

sed -i 's/wrapper.java.initmemory=2048/wrapper.java.initmemory=128/g' /opt/mycat/conf/wrapper.conf
sed -i 's/localhost:3316/localhost:3306/g' /opt/mycat/conf/schema.xml

cat > /etc/supervisord.d/mycat.conf << EOF
[program:mycat]
command=/opt/mycat/bin/run.sh
priority=501
autorestart=true
EOF
