- #### spring cloud config 加解密使用参考：
> 官方参考地址：http://cloud.spring.io/spring-cloud-static/Finchley.M9/single/spring-cloud.html#_security      
5.4 Encryption and Decryption部分


##### 配置环境：
> 先决条件：要使用加密和解密功能，您需要安装在JVM中的全功能JCE（默认情况下不存在）。您可以从Oracle下载“Java加密扩展（JCE）无限制强制管辖权策略文件”，并按照安装说明进行操作（基本上将JRE lib / security目录中的2个策略文件替换为您下载的那些文件）。

config server 的加密解密需要依赖与java Cryptography Extension (jce) 

java8 jce  
下载地址：http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html   

安装方式：可以参考里面的README,其实也很简单：把jdk下面/jre/lib/security 目录下面的两个jar替换了。


##### Config Server 配置：
执行如下命令：
```
$ keytool -genkeypair -alias mytestkey -keyalg RSA   -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US"   -keypass changeme -keystore server.jks -storepass letmein
```
会在当前目录生成server.jks文件，把server.jks文件复制到config server工程中的resource目录下,在
config server工程中的resource目录下新建bootstrap.properties配置文件
添加配置信息：
```
#支持加密存储
encrypt.key-store.location=server.jks
encrypt.key-store.password=letmein
encrypt.key-store.alias=mytestkey
encrypt.key-store.secret=changeme
```


**加密:**               
方法1、使用postman发送http://localhost:8881/encrypt  的post请求                     
方法2、
$ curl localhost:8881/encrypt -d mysecret                  
输出密文：
682bc583f4641835fa2db009355293665d2647dade3375c0ee201de2a49f7bda      
**解密：**     
方法1、使用postman发送http://localhost:8881/decrypt  的post请求        
方法2、
$ curl localhost:8881/decrypt -d 682bc583f4641835fa2db009355293665d2647dade3375c0ee201de2a49f7bda   
输出明文：
mysecret

##### 配置文件中使用密文：
密文的前面需要加上 **{cipher}** ，参考如下
```
spring.datasource.password={cipher}AIAjZUYm1nitDEviK6l3Oll4hgrWpod02W3fEWe4rsna6NbNh1uqRHnmPt4rUFOPm7TvIOJHcYyCnHywpfnkr+WC/BY0KPLlm5TPtywVKIgoQrFReCr5DCgLLWzlT7rb9OhWKEHi0NQCBNHX8i3uazvGw+kVgOdR6Y0j3VinWI/OLVQNyt7AXXMaQagaCXMsZVdq2aUv729FxVPZ6C9i16fT
```
