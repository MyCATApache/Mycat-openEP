
Mycat-openEP开放电商项目前端商城PHP实现
===

本项目是Mycat-openEP的一部分，以Yii2框架实现的电商网站.不包含后台管理功能，只实现前端的商品展示及购买流程支付等电商功能．

###使用[百度云盘下载的docker镜像](http://pan.baidu.com/s/1dDew2m1)遇到的一些问题

* 修改yum源配置文件/etc/yum.repos.d/thirdparty.repo

原因是系统中部分工具不全，需要使用yum升级安装，默认设置的yum源不正确
```
vim /etc/yum.repos.d/thirdparty.repo
#修改以下信息
[priv]
name=Priv Yum Repository
#baseurl=http://192.168.88.65/yumP
baseurl=http://mirrors.163.com/centos/$releasever/os/$basearch
```

* PHP扩展补充 
GD库安装
```
yum -y --enablerepo=remi,remi-php56 install php-gd
```


###Docker环境部署###

* 重新编译docker镜像中的IcePHP.so扩展
Docker镜像中默认安装的php版本和IcePHP.so扩展不匹配，
导致Ico无法使用，所以重新进行编译
```
 git clone https://github.com/zeroc-ice/ice
 git checkout  v3.5.1
 cd ice/php 
＃编辑
 vim  config  Make.rules.php
#此项可自行决定是否修改，不修改编译后的Ice将被安装到容器的/opt/Ice-3.5.1目录下
#prefix ?= /opt/Ice-$(VERSION) 
#因为yii2需要命令空间支持，所以我们编辑ice的编译配置文件，让编译的
#ice库代码以命名空间方式生成
USE_NAMESPACES          = yes

make
make install

#编译完成后，将编译得到的IcePHP.so扩展放到php扩展目录,重启php，查看ice
#扩展是否正常加载成功
/opt/Ice-3.5.1/php/IcePHP.so /usr/lib64/php/modules/IcePHP.so 

```

>> 注意
>> * 编译成功后/opt/Ice-3.5.1/php目录需要放到php的include_path路径中
>> * 项目代码中已经有针对php5.6.11编译好的IcePHP.so扩展文件，可直接使用,将其拷贝到容器
中的/usr/lib64/php/modules/IcePHP.so目录,配置php加载即可
>> * 项目代码shop-web/php/vendor/ice文件夹下的ice_ns目录为ice运行需要的php文件


* docker中的目录映射

    假设Docker只作为开发环境，PHP代码在宿主机中 

    宿主机项目代码所在路径为:

         /data/github/Mycat-openEP/shop-web/php

    在启动docker容器时，添加如下参数将宿主机中的代码目录映射到容器中的 /opt/www/openep:
```
       -v /data/github/Mycat-openEP/shop-web/php:/opt/www/openep
```
>>关于docker启动，请阅读[Mycat Openep Docker 使用说明](../../docker/README.md)


* 在容器中配置nginx指向openop/web目录 (nginx主机配置文件路径为/etc/nginx/conf.d)
```
server {
      server_name example.openep.com;
      root /opt/www/openep/php/openep/web;
    
      location / {
          try_files $uri $uri/ /index.php;
          include php.conf;
          include expire.conf;
      }
      access_log /var/log/nginx/example.openep.com.log main;
}
```

* 重启nginx
```
supervisorctl restart nginx

```

配置完成后,登录容器,进入ice管理后台,启动订单服务
前台访问绑定的域名，即可成功运行demo
```
#默认密码123456
ssh root@127.0.0.1 -p2222
icegridadmin -u root -p 123456  --Ice.Default.Locator="IceGrid/Locator:tcp -h localhost -p 4061"
server start TicketOrderServer1
```

 ![Mycat-php-demo](Mycat-php-demo.png)