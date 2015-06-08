#!/bin/bash

yum -y --enablerepo=zeroc-ice install ice-servers ice-java ice-java-devel ice-php ice-php-devel

mkdir -p /opt/ice/{registry,node1}
cp *.cfg /opt/ice

cat > /etc/supervisord.d/ice.conf << EOF
[program:ice-registry]
command=/usr/bin/icegridregistry --Ice.Config=/opt/ice/registry.cfg
autorestart=true

[program:ice-node1]
command=icegridnode --Ice.Config=/opt/ice/node1.cfg
autorestart=true
EOF