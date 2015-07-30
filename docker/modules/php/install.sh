#!/bin/bash

yum -y --enablerepo=remi,remi-php56 install php php-fpm php-mysqlnd php-opcache php-mbstring php-mcrypt php-soap php-gd
rpm -e -nodeps --nodeps redhat-logos

sed -i 's/upload_max_filesize = 2M/upload_max_filesize = 100M/g' /etc/php.ini
sed -i 's/post_max_size = 8M/post_max_size = 100M/g' /etc/php.ini
sed -i 's/daemonize = yes/daemonize = no/g' /etc/php-fpm.conf
sed -i 's/;catch_workers_output = yes/catch_workers_output = yes/g' /etc/php-fpm.d/www.conf

cat > /etc/supervisord.d/php-fpm.conf << EOF
[program:php-fpm]
command=/usr/sbin/php-fpm
autorestart=true
EOF