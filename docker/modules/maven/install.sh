#!/bin/bash

ver='3.3.3'
pkg="apache-maven-${ver}-bin.tar.gz"
dest='/opt/javahome'

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget http://apache.fayea.com/maven/maven-3/${ver}/binaries/${pkg}

cp mvn.sh /etc/profile.d/
mkdir -p ${dest}
mkdir ~/.m2
cp settings.xml ~/.m2
tar zxf ${pkg} -C ${dest}
cd ${dest}
ln -s apache-maven-${ver} maven
