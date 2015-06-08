#!/bin/bash

[ -d ~/.oh-my-zsh ] && exit

yum -y install zsh

if [ -d "../cache/oh-my-zsh" ]; then
    cp -a ../cache/oh-my-zsh ~/.oh-my-zsh
else
    git clone git://github.com/robbyrussell/oh-my-zsh.git ~/.oh-my-zsh
fi

wget -O /etc/profile.d/autojump.zsh https://raw.githubusercontent.com/joelthelion/autojump/master/bin/autojump.zsh
sed -i 's/--add/-a/g' /etc/profile.d/autojump.zsh
cp zshrc ~/.zshrc
chsh -s /bin/zsh