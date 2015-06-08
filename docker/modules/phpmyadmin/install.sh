#!/bin/bash

ver='4.4.9'
pkg="phpMyAdmin-${ver}-all-languages.tar.gz"
dest='/opt/www'
prg=`pwd`

[ -f "../cache/${pkg}" ] && pkg="../cache/${pkg}" || wget http://jaist.dl.sourceforge.net/project/phpmyadmin/phpMyAdmin/${ver}/${pkg}

mkdir -p ${dest}
tar zxf ${pkg} -C ${dest}
cd ${dest}
mv phpMyAdmin-${ver}-all-languages phpmyadmin
cp ${prg}/config.inc.php phpmyadmin
