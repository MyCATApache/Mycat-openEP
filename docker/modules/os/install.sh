#!/bin/bash

[ -d /root/.ssh ] && exit

# Ssh
ssh-keygen -q -N "" -t dsa -f /etc/ssh/ssh_host_dsa_key
ssh-keygen -q -N "" -t rsa -f /etc/ssh/ssh_host_rsa_key
sed -ri 's/session    required     pam_loginuid.so/#session    required     pam_loginuid.so/g' /etc/pam.d/sshd
echo "UseDNS no" >> /etc/ssh/sshd_config
sed -i "s/#Port 22/Port 2222/" /etc/ssh/sshd_config
sed -i "s/GSSAPIAuthentication yes/GSSAPIAuthentication no/" /etc/ssh/sshd_config
sed -i "s/GSSAPICleanupCredentials yes/GSSAPICleanupCredentials no/" /etc/ssh/sshd_config
mkdir -p /root/.ssh && chown root.root /root && chmod 700 /root/.ssh
echo 'root:123456' | chpasswd

# Date
rm -rf /etc/localtime
ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
date

# Yum
yum -y update
yum -y --enablerepo=rpmforge-extras install samba bash-completion autojump python-pip htop ncdu dstat iotop iftop nload nethogs

# Supervisor
pip install supervisor
mkdir -p /var/log/supervisor
sed -i 's/meld3 >= 0.6.5/#meld3 >= 0.6.5/g' /usr/lib/python2.6/site-packages/supervisor-3.1.3-py2.6.egg-info/requires.txt

# Rsyslog
sed -i 's/^\$ModLoad imklog/#\$ModLoad imklog/g' /etc/rsyslog.conf

# Crontab
sed -i 's/session    required   pam_loginuid.so/#session    required   pam_loginuid.so/g' /etc/pam.d/crond

# Samba
cat > /etc/samba/smb.conf << EOF
[global]
    workgroup = workgroup
    server string = Openep Files Server
    netbios name = openepfs
    security = user
    load printers = no
    printing = bsd
    printcap name = /dev/null
    disable spoolss = yes
    hide dot files = no
    passdb backend = smbpasswd
    syslog only = no
    follow symlinks = yes
    wide links = yes
    unix extensions  = no
[root]
    comment = root
    path = /
    browseable = yes
    guest ok = no
    writable = yes
    write list = root

EOF
echo -e '123456\n123456'|smbpasswd -sa root


# Env
mkdir -p /opt/extdata
chmod 777 /tmp
chown root.root /etc
chmod 775 /var/run/screen
echo -e '\n\n\n# Add Content before this line\ntail -f /dev/null'>>/etc/rc.local

cat >> /etc/inputrc << EOF
set completion-ignore-case on
"\e[A": history-search-backward
"\e[B": history-search-forward
EOF

cat >> /etc/vimrc << EOF
syntax on                        "语法高亮
filetype plugin indent on        "开启插件
"set number                      "显示行号
set nocompatible                 "不兼容vi模式
set modelines=0                  "不使用modelines
set modelines=2                  "modeline检测2行
set tabstop=4                    "设置tap键为4个空格
set shiftwidth=4                 "设置当行之间交错时使用4个空格, << 和 >> 命令移动时的宽度为4
set softtabstop=4                "使得按退格键时可以一次删掉 4 个空格
set expandtab                    "设置缩进长度
set showmatch                    "设置匹配模式 显示括号对应
set ruler                        "打开状态栏标尺
set incsearch                    "实时显示搜索结果
set scrolloff=3                  "当光标与顶部距离为三行时发生翻滚, 且翻滚后光标与底部相距三行
set autoindent                   "自动对齐方式
set smartindent                  "智能对齐方式
set showmode                     "显示当前模式
set showcmd                      "在窗口右下角显示完整命令已输入部分
set hidden                       "允许在有未保存的修改时切换缓冲区, 此时的修改由vim负责保存
set wildmenu                     "启用文本模式的菜单
set wildmode=list:longest        "增强模式打开列表
set visualbell                   "可视化铃声
set cursorline                   "高亮光标所在行
set ttyfast                      "设置快速终端
set encoding=utf-8               "选择编码
set nobackup                     "缺省不产生备份文件
set autochdir                    "自动切换当前目录为当前文件所在的目录
set laststatus=2                 "总是显示状态栏
set backspace=indent,eol,start   "设置backspace的工作方式 不兼容vi
set statusline=\ %<%F[%1*%M%*%n%R%H]%=\ %y\ %0(%{&fileformat}\ %{&encoding}\ %c:%l/%L(%p%%)%)   "设置在状态行显示的信息
set fileencodings=ucs-bom,utf-8,utf-16,cp936,gb18030,big5,euc-jp,euc-kr,latin1                  "自动识别编码, 正确显示中文
EOF

cat >> /etc/screenrc << EOF
term xterm-256color
vbell off
altscreen on
shell -\$SHELL
attrcolor b ".I"
defscrollback 10000
startup_message off
termcapinfo xterm* 'is=\E[r\E[m\E[2J\E[H\E[?7h\E[?1;4;6l'
hardstatus alwayslastline
hardstatus string "\${-}%{.0c}%-w%{.y0}%f%n %t%{-}%+w %=[%H] %c | Load: %l"
EOF

mkdir -p /root/.config/htop
cat > /root/.config/htop/htoprc << EOF
hide_threads=0
hide_kernel_threads=1
hide_userland_threads=1
shadow_other_users=1
EOF
