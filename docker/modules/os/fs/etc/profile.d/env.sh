#!/bin/bash

stty erase ^H

export PS1='[\u@\h \W]\$'
export TMOUT=21600
export TIME_STYLE='+%y-%m-%d %H:%M'

export PATH=$PATH:$HOME/bin