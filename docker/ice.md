# Ice服务说明
------

Docker镜像中已经包括了Ice运行时环境，并且自动启动了Ice Demo服务
ice的java环境目录是在/opt/javahome/jdk下
ice的控制台日志在var/log/supervisor/ice-xxxx，分注册表、node节点，node的日志区分为ice-nodexxx-stdout与ice-nodexxx-stderr---supervisor-xxxx.log
需要两个日志都观察，以确定日志问题
ice服务的实现类和接口类分别打包，放在/opt/openep/java/lib下，如果更新Jar，则需要重启Ice服务，参照下面的部署
### ice相关管理操作
Ice服务的发布，升级，可以进入icgridamdin控制台
进入grid的命令行，其中 -h localhost表明Docker镜像所在的主机的IP地址，替换为自己的Ip
icegridadmin -u test -p test --Ice.Default.Locator="IceGrid/Locator:tcp -h localhost -p 4061"
openep gridx.xml文件 /opt/openep/openepgrid.xml

* 添加grid应用
```
application add /opt/openep/openepgrid.xml
```
* 更新grid应用
```
application update /opt/openep/openepgrid.xml
```
* 查看Server
```
server list
```
* 启动某个Server
```
server start TicketOrderServer1
```

如果启动失败，查看ice的控制台日志

群共享的 iceTicketProject工程包括所有源码，和客户端测试代码TestICE

* src/service是服务接口定义，实现类分为两部分service/impl/是Ice的部分，由于具体业务逻辑是在Spring中实现的，因此还有一个Spring的类：
* src/spring/TicketOrderServiceSpringImp.java，这就是大家熟知的Spring方式的实现类，
如果本机客户端来访问上述服务，需要把ice locator地址改为 此Docker镜像所在主机的地址
* src/iceclient.properties里修改IceLocator地址，运行IceTicketProject 里的 TestICE 访问上述服务

#### 重新编辑PHP的IcePHP.so扩展
Docker镜像中默认安装的php版本和IcePHP.so扩展不匹配，导致Ico无法使用，所以重新进行编译
具体过程请迁步[至此](../shop-web/php/README.md) 