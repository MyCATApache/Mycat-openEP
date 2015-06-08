#!/bin/bash

pkg='jdk-8u20-linux-x64.tar.gz'
ver='jdk1.8.0_20'
dest='/opt/javahome'

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget https://www.reucon.com/cdn/java/${pkg}

cp jdk.sh /etc/profile.d/
mkdir -p ${dest}
tar zxf ${pkg} -C ${dest}
cd ${dest}
ln -s ${ver} jdk8
[ ! -d jdk ] && ln -s ${ver} jdk

rm -f ${ver}/src.zip
rm -rf ${ver}/db
