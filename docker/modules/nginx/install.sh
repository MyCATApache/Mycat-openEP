#!/bin/bash

yum -y --enablerepo=nginx install nginx
rm -rf /etc/nginx/*
cp -a conf/* /etc/nginx

cat > /etc/supervisord.d/nginx.conf << EOF
[program:nginx]
command=/usr/sbin/nginx
autorestart=true
EOF