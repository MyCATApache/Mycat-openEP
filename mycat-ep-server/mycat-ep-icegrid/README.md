
#MyCat-EP-ICEGrid 

##运行ICEGRID 步骤说明：

###第一步 在模块 mycat-ep-parent 上 执行 package or install
此步骤会打出各个模块的包

###第二步 使用 mycat-ep-icegrid-server 模块打出的包 上传到服务器解压目录 
 在解压的icegrid目录内执行 
    
  ```   
    #chmod -R 777 logs
    #chmod +x bin/*
  ```
 
 patch 之前 配置好jdbc jedis 等等 
 配置文件目录：patch/mycat_ep_system/config/
 
 patch 前计算文件签名
 
 ```
    #icepatch2calc ./patch/
 ```
 
 启动服务
 
 ```
    #bin/registry.sh start master
    #bin/node.sh start node1
    #bin/admin.sh 
    >>> application add config/mycat_ep_system.xml
 ```
 
好了，就这么简单服务就完成了 当然 各个服务模块还未调试 还有Bug 等着你修改 


###第三 客户端调用  

客户端也是非常容易的：
引用的包

```
    <!--IceClientUtil -->
    <dependency>
        <groupId>io.mycat.ep</groupId>
        <artifactId>mycat-ep-icegrid-client</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    <!-- 用户服务client 包 -->
    <dependency>
            <groupId>io.mycat.ep</groupId>
            <artifactId>mycat-ep-user-client</artifactId>
            <version>1.0.0-SNAPSHOT</version>
    </dependency>
```
    
其他服务模块类似 user client 引入
Spring 加入下面配置：

 ```
    <context:property-placeholder location="classpath:iceclient.properties" file-encoding="UTF-8"/>
    <context:component-scan base-package="io.mycat.ep" />
 ```
     
iceclient.properties 配置文件的内容
        
      
        Ice.Default.Locator=MyIceGrid/Locator:default -h 192.168.0.221 -p 13000:default -h 192.168.0.221 -p 13001
        Ice.idleTimeOutSeconds=300
 
JAVA代码调用方式：
     
 
    //得到 用户服务代理类            
    UserHandlerPrx userHandlerPrx = (UserHandlerPrx) ICEClientUtil.getSerivcePrx(UserHandlerPrx.class);
    //调用注册方法
    UserSession session = userHandlerPrx.regist(clientInfo) ;
 
 
End.
