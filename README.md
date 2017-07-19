# activemq-mongo-plugs
activemq 插件  activemq mongo 存储

对监听到的文本消息启动存储到mongodb

启动方法
-------
1.在你的activemq中的lib文件夹下放入需要的jar包

my-plugs-5.14.5.jar (本项目的target下)
fastjson           (解析消息体时用到的)
mongo-java-driver   (mongo连接)

2.把conf下的mongo.properties放入activemq下的conf文件夹下

3.修改activemq.xml
在broker下添加
```xml
        <plugins>
            <loggingBrokerPlugin logAll="true"></loggingBrokerPlugin>
            <bean xmlns="http://www.springframework.org/schema/beans" id="serviceRequestPlugin"
                  class="my.plugins.plugin.MongoDBPlugin">
            </bean>
        </plugins>
```
4.启动activemq start
启动成功






二次开发
------- 

1.下载源码

可以下载activemq源码   https://github.com/apache/activemq

帮助文档   http://www.jianshu.com/p/364d8aa761fd

我用的是5.14.5(因为据说5.15开始用jdk8了)

2.把本项目放入项目中

注意要把mongo.properties信息修改为你的mongo信息

在activemq-parent中添加本项目的model

在activemq-web-console/src/webapp/WEB-INF/activemq.xml中添加
```xml
        <plugins>
            <loggingBrokerPlugin logAll="true"></loggingBrokerPlugin>
            <bean xmlns="http://www.springframework.org/schema/beans" id="serviceRequestPlugin"
                  class="my.plugins.plugin.MongoDBPlugin">
            </bean>
        </plugins>
```
3.重新install
```shell
mvn clean install Dmaven.test.skip=true
```
4.启动activemq-web-console项目的jetty
```info
2017-07-19 13:10:01,713 [main           ] INFO  ndingBeanNameUrlHandlerMapping - Mapped URL path [/deleteJob.action] onto handler '/deleteJob.action'
2017-07-19 13:10:01,713 [main           ] INFO  ndingBeanNameUrlHandlerMapping - Mapped URL path [/retryMessage.action] onto handler '/retryMessage.action'
[INFO] Started o.e.j.m.p.JettyWebAppContext@43732a1c{/,file:/D:/ideawork/activemqresource/activemq/activemq/activemq-web-console/src/main/webapp/,AVAILABLE}{file:/D:/ideawork/activemqresource/activemq/activemq/activemq-web-console/src/main/webapp/}
[WARNING] !RequestLog
[INFO] Started ServerConnector@3b996d53{HTTP/1.1}{0.0.0.0:8080}
[INFO] Started @10386ms
[INFO] Started Jetty Server
[INFO] Starting scanner at interval of 10 seconds.
```
5.完成
