#!/bin/bash

hash febootstrap 2>/dev/null || yum -y install febootstrap

febootstrap -i centos-release -i yum -i yum-utils \
-i coreutils -i diffutils -i findutils -i iputils -i bind-utils -i net-tools \
-i crontabs -i man -i which -i patch -i xz -i tar -i zip -i bzip2 -i unzip \
-i passwd -i file -i lsof -i lrzsz -i screen -i tree -i vim-minimal -i vim-enhanced \
-i wget -i rsync -i traceroute -i iproute -i ftp -i telnet -i nc -i openssh-server -i openssh-clients \
centos centos http://mirrors.aliyun.com/centos/6/os/x86_64/

cd centos
tar -c .|docker import - centos-6.6