#!/bin/bash

pkg='jdk-7u79-linux-x64.tar.gz'
ver='jdk1.7.0_79'
dest='/opt/javahome'

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget https://www.reucon.com/cdn/java/${pkg}

cp jdk.sh /etc/profile.d/
mkdir -p ${dest}
tar zxf ${pkg} -C ${dest}
cd ${dest}
ln -s ${ver} jdk7
[ ! -d jdk ] && ln -s ${ver} jdk

rm -f ${ver}/src.zip
rm -rf ${ver}/db
