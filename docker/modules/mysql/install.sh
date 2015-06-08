#!/bin/bash

yum -y install mysql-community-server

mkdir -p /opt/data/mysql
mkdir -p /var/log/mysql
cp my.cnf /etc
service mysqld start

mysql << EOF
create database db1;
create database db2;
create database db3;
EOF

mysqladmin -u root password "123456"

service mysqld stop

cat > /etc/supervisord.d/mysql.conf << EOF
[program:mysql]
command=/usr/bin/pidproxy /var/run/mysqld/mysqld.pid /usr/bin/mysqld_safe
priority=500
EOF