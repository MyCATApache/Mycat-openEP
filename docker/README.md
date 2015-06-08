# Mycat Openep Docker 使用说明

------

本项目提供了快速启动mycat openep环境能力, 方便快速参与开发, 同时作为dockerfile最佳实践, 是学习docker构建的极佳素材,优点如下:

> * 模块化布局, 很方便加入新功能
> * 主要安装代码一次执行, 提高了构建速度
> * 提供download包缓存, 提高构建速度
> * 采用supervisord作为启动工具, 强大高效
> * 根据作者多年运维优化经验, 各组件做了充分配置优化, 占用内存小

# 构建镜像

### 1. 构建centos基础镜像

```bash
./build.sh
```
此命令将新建一个名为 centos-6.6 的基础镜像

### 2. 构建openep镜像

```bash
./build.sh
```
此命令将新建一个的名为 openep 的镜像
*注: 依赖包较多, 可能会很慢, 稍安勿躁*

### 3. 启动容器

```bash
./run.sh openep [containerName]
```

# 直接导入

因为国内访问dockerhub很慢, 已将镜像导入上传, [点击去下载](http://pan.baidu.com/s/1dDew2m1)

导入:
```bash
zcat openep-1.0.tgz | docker import - openep
```

启动:

```bash
./run-flat.sh openep [containerName]
```



# 使用说明

组件列表:

| 组件          |  开放端口   | 说明                                   |
| ----          | -----       | ----                                   |
| os           |            | 基础系统                                 |
| scm          |            | git, svn                                 |
| zsh          |            | 好用的shell                              |
| jdk7         |            |                                       |
| jdk8         |            |                                       |
| maven        |            | java构建工具                             |
| mycat        | 8066 9066  | 数据库中间件                             |
| mysql        | 3306       | 数据库                                   |
| nginx        | 80         | web服务器                                |
| php          |            |                                       |
| phpmyadmin   |            | 访问地址: http://host:port/phpmyadmin   |
| tomcat       | 8080       | servlet容器                              |
| probe        |            | tomcat管理工具: http://host:port/probe  |
| zookeeper    | 2181       | 集群工具                                 |
| ice          | 4061 8888  | rpc中间件                                |

注:所有密码都是root 123456

# 提高构建速度
 1. 下载离线包 
 [点击去下载](http://pan.baidu.com/s/1dDew2m1)
 1. 使用预缓存包
 解压cache.zip到modules/cache目录
 2. 构建自己的yum源
 解压rpms.tgz到任意http服务器中, 然后修改 modules/os/fs/etc/yum.repos.d/thirdparty.repo 修改[priv]地址为正确地址, enabled改成1
 
# Faq

1. 如何查看映射的端口:
```bash
docker ps
```