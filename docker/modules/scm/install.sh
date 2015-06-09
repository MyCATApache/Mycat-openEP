#!/bin/bash

yum -y --enablerepo=rpmforge-extras install git tig
#yum -y --enablerepo=rpmforge-extras install subversion
git config --global user.name "root"
git config --global user.email "root@localhost"
git config --global color.ui "auto"
git config --global credential.helper "store"