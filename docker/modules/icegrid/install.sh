#!/bin/bash

mkdir -p /opt/icegrid/{bin,config,db,logs,patch,pid}

cp  ./bin/* /opt/icegrid/bin/
cp  ./config/* /opt/icegrid/config/

chmod +x /opt/icegrid/bin/*
chmod -R 777 /opt/icegrid/logs

cat > /etc/supervisord.d/icegrid.conf << EOF
[program:icegrid-registry-master]
command=/opt/icegrid/bin/registry.sh start master
priority=501
autorestart=true

[program:icegrid-registry-slave]
command=/opt/icegrid/bin/registry.sh start slave
priority=502
autorestart=true

[program:icegrid-node1]
command=/opt/icegrid/bin/node.sh start node1
priority=503
autorestart=true
EOF
