# Mycat Openep Docker 使用说明

------

本项目提供了快速启动mycat openep环境能力, 方便快速参与开发, 同时作为dockerfile最佳实践, 是学习docker构建的极佳素材,优点如下:

> * 模块化布局, 很方便加入新功能
> * 主要安装代码一次执行, 提高了构建速度
> * 提供download包缓存, 提高构建速度
> * 采用supervisord作为启动工具, 强大高效
> * 根据作者多年运维优化经验, 各组件做了充分配置优化, 占用内存小

# 第三方docker资料

### [docker安装包快速下载](http://pan.baidu.com/s/1c0jZvH2)

[docker入门指南][1]

[mac docker安装手册][2]

[windows docker安装手册][3]

# Docker环境准备


~~可以用Boot2Docker在Windows上安装，或者导入已经安装好整个平台的Redhat Atomict 虚机镜像~~
~~虚机镜像的下载地址为百度盘：http://pan.baidu.com/s/1qWE8KFi~~
~~虚机的用户名密码 root/mycat1314~~
~~虚机网络，eth0为NAT网络，eth1为一个Host-Only的子网，子网网段为172.16.0.0/16 ，其中IP地址固定为172.16.88.8 ，需要自己配置VirtualBox或VM的网络并绑定到虚机的eth1网卡上。~~
~~运行 docker ps -a 查询mycat ep的镜像~~
~~docker start xxxxcontainID 启动镜像~~

~~浏览器访问 http://172.16.88.8:9001  用户名密码root/123456~~
 


# 直接导入镜像

因为国内访问dockerhub很慢, 已将镜像导入上传, [点击去下载](http://pan.baidu.com/s/1c0jZvH2)

导入:
```bash
gunzip -c openep_1.3.tgz | docker import - openep:1.3
```
openep_1.3 镜像包括ICEGRID 部署的各服务模块

windows ，mac 环境访问docker 内部网络，需要添加到 docker 私有网络的路由

例如：以 docker私有网络 172.17.0.0/24  ， VM 虚拟机IP 192.168.99.100 为例：

MAC 平台：
```
route -n add -net 172.17.0.0/24 192.168.99.100
```

windows 平台
```
route add 172.17.0.0 mask 255.255.255.0 192.168.99.100
```



# 构建镜像

### 1. 构建centos基础镜像, 仅支持centos6

```bash
./centos.sh
```
此命令将新建一个名为 centos-6.6 的基础镜像

### 2. 构建openep镜像

```bash
./build.sh
```
此命令将新建一个的名为 openep 的镜像

*注: 依赖包较多, 可能会很慢, 稍安勿躁*

# 启动容器

下载启动脚本
```
wget https://raw.githubusercontent.com/MyCATApache/Mycat-openEP/master/docker/run.sh
wget https://raw.githubusercontent.com/MyCATApache/Mycat-openEP/master/docker/run-host.sh
chmod +x run*.sh
```

host方式启动(共享宿主网络, 推荐, 但是只能启动一个实例)
```bash
./run-host.sh openep [containerName]
```

bridge方式启动(docker私有网络, 默认)
```bash
./run.sh openep [containerName]
```

# 使用说明

组件列表:

| 组件         |  开放端口  | 说明                                   |
| ----         | -----      | ----                                   |
| ssh          | 2222       | ssh服务                                |
| supervisor   | 9001       | 服务管理                               |
| samba        | 139,445    | 文件共享                               |
| scm          |            | git                                    |
| zsh          |            | 好用的shell                            |
| jdk7         |            |                                        |
| jdk8         |            |                                        |
| maven        |            | java构建工具                           |
| mycat        | 8066 9066  | 数据库中间件                           |
| mysql        | 3306       | 数据库                                 |
| nginx        | 80         | web服务器                              |
| php          |            |                                        |
| phpmyadmin   |            | 访问地址: http://host:port/phpmyadmin  |
| tomcat       | 8080       | servlet容器                            |
| probe        |            | tomcat管理工具: http://host:port/probe |
| zookeeper    | 2181       | 集群工具                               |
| ice          | 12000 12001| rpc中间件                              |

# 提高构建速度

1. 下载离线包 
[点击去下载](http://pan.baidu.com/s/1dDew2m1)
2. 使用预缓存包
解压cache.zip到modules/cache目录
3. 构建自己的yum源
解压rpms.tgz到任意http服务器中, 然后修改 modules/os/fs/etc/yum.repos.d/thirdparty.repo 修改[priv]地址为正确地址, enabled改成1
 
# 如何管理

1. 直接ssh登录2222端口 
2. 通过samba文件共享访问 \\\\hostip\root, (mac是: smb://hostip/root)
3. 通过supervisor http管理服务: http://hostip:9001/
4. 通过probe管理tomcat: http://hostip:8080/probe
5. 通过phpmyadmin管理mysql http://hostip/phpmyadmin

*注1:所有密码都是root 123456*

*注2:如果是nat方式启动,请先用docker ps查看对应映射端口*

# 服务管理

 - 通过supervisor http管理服务: http://hostip:9001/
 - supervisorctl help
  查看帮助
 - supervisorctl status
  查看服务状态
 - supervisorctl start mycat
  启动服务
 - supervisorctl stop mycat
  停止服务
 - supervisorctl restart mycat
  重启服务

# Faq

1. 如何查看映射的端口:
docker ps

2. 如何查看ice sdk安装地址
```bash
rpm -ql ice-java
rpm -ql ice-php
rpm -ql ice-python
```

[1]: http://www.widuu.com/chinese_docker/
[2]: http://www.widuu.com/chinese_docker/installation/macos.html
[3]: http://www.widuu.com/chinese_docker/installation/windows.html