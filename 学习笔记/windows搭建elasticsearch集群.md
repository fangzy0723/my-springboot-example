---
title: windows搭建elasticsearch集群
---

> 首先确保已经安装了node环境,JDK版本1.8+

- #### 下载elasticsearch到本地并解压

> 访问地址 https://www.elastic.co/cn/downloads/elasticsearch

![image](https://upload-images.jianshu.io/upload_images/7030886-59b1c7c9a3541e57.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700) 

![image](https://upload-images.jianshu.io/upload_images/7030886-d80f22833e1cf9bd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

选择下载ZIP，放到本地盘符的一个文件夹下，解压之后如图示例，我是复制了两份，node1当作主节点，node2、node3当作从节点

![image](https://upload-images.jianshu.io/upload_images/7030886-f4bb49de7fcb0b83.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

<!-- more -->

- #### 修改主节点配置文件

> 进到elasticsearch-6.1.1 -node1/config的文件夹中 如下图所示文件

![image](https://upload-images.jianshu.io/upload_images/7030886-30d3feda554aab86.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

需要修改es相关配置信息需修改elasticsearch.yml文件

需要修改jvm相关配置信息需修改lvm.options文件

需要修改es相关配置信息需修改log4j2.properties文件


> 编辑elasticsearch.yml文件，配置如下图所示

![image](https://upload-images.jianshu.io/upload_images/7030886-9e377b42c757b81e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/575)

配置http.cors.enabled: true      http.cors.allow-origin: "*"  参数，为了head插件可以访问ES

cluster.name: elasticsearch  集群名，以此作为是否同一集群的条件

node.name: master   节点名，以此作为集群中区分不同节点的的条件

node.master: true   是否为主节点

network.host: 127.0.0.1   网络地址

http.port: 9200        端口

path.data  数据存储地址

path.log   日志存储地址

启动elasticsearch主节点，双击bin/elasticsearch.bat，如图所示

![image](https://upload-images.jianshu.io/upload_images/7030886-921084a85f765436.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

如下图所示表示启动成功

![image](https://upload-images.jianshu.io/upload_images/7030886-e5324e5c2c2f4abc.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

在浏览器中访问地址 http://127.0.0.1:9200/，如下页面显示节点信息，启动成功

![image](https://upload-images.jianshu.io/upload_images/7030886-38a7b24958bb00db.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

- #### 修改从节点配置文件

依次进到elasticsearch-6.1.1 -node2/config、elasticsearch-6.1.1 -node3/config的文件夹，修改从节点elascticsearch配置文件分别如下图所示

![image](https://upload-images.jianshu.io/upload_images/7030886-bdb9262c25ef0cd0.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/643)

![image](https://upload-images.jianshu.io/upload_images/7030886-e2da69b0d23963ad.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/626)

依次启动elasticsearch从节点，可参考主节点启动方式。如下图表示启动成功

![image](https://upload-images.jianshu.io/upload_images/7030886-84f9ba5e0986a991.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

浏览器地址栏访问地址http://127.0.0.1:9200/_cat/nodes?v，显示页面如下图表示添加从节点成功

![image](https://upload-images.jianshu.io/upload_images/7030886-d8de3cd60b2dcb64.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

- #### 安装head插件

> 安装grunt, 执行命令 npm install -g grunt-cli 进行全局安装grunt，如下图所示


![image](https://upload-images.jianshu.io/upload_images/7030886-084f2d6f308d9946.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

执行命令grunt -version显示版本号表示安装成功，如图所示

![image](https://upload-images.jianshu.io/upload_images/7030886-8e2d6c59f8c04ea6.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/626)
 
浏览器地址栏访问https://github.com/mobz/elasticsearch-head下载head插件，如图

![image](https://upload-images.jianshu.io/upload_images/7030886-d72a551717cfb2c2.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

> 点击Download ZIP下载到本地，或者用git执行命令 git clone git://github.com/mobz/elasticsearch-head.git 下载也可以，把下载的elasticsearch-head文件放到一个文件夹下，我是放到如下路径

![image](https://upload-images.jianshu.io/upload_images/7030886-744845c6ec44af89.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

在控制台进入到E:\work_soft\ElasticStack\elasticsearch-head目录下,执行npm install命令，如下图所示

![image](https://upload-images.jianshu.io/upload_images/7030886-4e5cbb3d8314d112.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

等待安装完成，然后执行命令 npm run start启动服务

![image](https://upload-images.jianshu.io/upload_images/7030886-6a632ee965a72cdc.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

在浏览器地址栏访问地址http://127.0.0.1:9100/，显示如下页面表示elasticsearch集群搭建完成，一主二从

![image](https://upload-images.jianshu.io/upload_images/7030886-b935242cd1c3c1a4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)
