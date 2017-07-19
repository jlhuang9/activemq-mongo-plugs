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
        <plugins>
            <loggingBrokerPlugin logAll="true"></loggingBrokerPlugin>
            <bean xmlns="http://www.springframework.org/schema/beans" id="serviceRequestPlugin"
                  class="my.plugins.plugin.MongoDBPlugin">
            </bean>
        </plugins>

4.启动activemq start
启动成功






如果想二次开发
可以下载activemq源码   https://github.com/apache/activemq
帮助文档   http://www.jianshu.com/p/364d8aa761fd
我用的是5.14.5(因为据说5.15开始用jdk8了)
