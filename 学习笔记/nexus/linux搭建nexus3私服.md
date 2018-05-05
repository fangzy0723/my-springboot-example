nexus搭建流程：
1、下载nexus
oss是免费版
下载地址：https://www.sonatype.com/download-oss-sonatype?utm_referrer=https%3A%2F%2Fwww.sonatype.com%2Fnexus-repository-oss

2、Centos下安装Nexus
先确保安装jdk8+
2.1解压缩文件
用WinSCP将下载的安装包上传到128.232.6.23机器上的/home/xbt/nexus目录下
执行tar -zxvf nexus-3.9.0-01-unix.tar.gz命令进行解压，得到nexus-3.9.0-01、sonatype-work两个文件
2.2配置环境变量
执行命令vi /etc/profile，编辑文件添加内容：export NEXUS_HOME=/home/xbt/nexus/nexus-3.9.0-01 然后保存并退出
执行命令source /etc/profile 使添加的内容生效
进入到目录/home/xbt/nexus/nexus-3.9.0-01/bin/下编辑修改nexus.rc文件的内容为run_as_user="root"，设置启动该服务的用户
2.3添加服务
执行命令sudo ln -s $NEXUS_HOME/bin/nexus /etc/init.d/nexus  把nexus添加到服务
2.4启动nexus
执行命令service nexus start
控制台显示Starting nexus表示启动完成

默认端口是8081 
默认用户名是admin,密码admin123 
修改端口在：/home/xbt/nexus/nexus-3.9.0-01/etc/nexus-default.properties文件

在浏览器输入http://128.232.6.23:18081/能正常访问页面说明启动成功，端口改成了18081

maven的setting文件参考如下配置：
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <!-- Linux环境下的setting -->
    <localRepository>D:\CiticPru\repository-xincheng-xbt</localRepository>
    <interactiveMode>true</interactiveMode>
    <offline>false</offline>
    <pluginGroups>
        <!--<pluginGroup>org.mortbay.jetty</pluginGroup>
        <pluginGroup>org.jenkins-ci.tools</pluginGroup>-->
    </pluginGroups>

    <!--配置权限,使用默认用户-->
    <servers>
        <server>
            <id>maven-central</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
        <server>
            <id>maven-public</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
        <server>
            <id>maven-releases</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
        <server>
            <id>maven-snapshots</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
    </servers>

    <mirrors>

    </mirrors>

    <profiles>
        <profile>
            <id>setting</id>
            <activation>
                <activeByDefault>false</activeByDefault>
                <jdk>1.8</jdk>
            </activation>
            <repositories>
                <!-- 私有库地址-->
                <repository>
                    <id>maven-public</id>
                    <url>http://128.232.6.23:18081/repository/maven-public/</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>maven-snapshots</id>
                    <url>http://128.232.6.23:18081/repository/maven-snapshots/</url>
                    <releases>
                            <enabled>true</enabled>
                    </releases>
                    <snapshots>
                            <enabled>true</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>maven-releases</id>
                    <url>http://128.232.6.23:18081/repository/maven-releases/</url>
                    <releases>
                            <enabled>true</enabled>
                    </releases>
                    <snapshots>
                            <enabled>true</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>maven-central</id>
                    <url>http://128.232.6.23:18081/repository/maven-central/</url>
                    <releases>
                            <enabled>true</enabled>
                    </releases>
                    <snapshots>
                            <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
            <pluginRepositories>
                <!--插件库地址-->
                <pluginRepository>
                    <id>maven-public</id>
                    <url>http://128.232.6.23:18081/repository/maven-public/</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                   </snapshots>
                </pluginRepository>
            </pluginRepositories>
        </profile>
        <profile>  
            <id>jdk18</id>  
            <activation>  
                <activeByDefault>true</activeByDefault>  
                <jdk>1.8</jdk>  
            </activation>  
            <properties>  
                <maven.compiler.source>1.8</maven.compiler.source>  
                <maven.compiler.target>1.8</maven.compiler.target>
                <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
            </properties>   
        </profile>

    </profiles>

    <!--激活profile-->
    <activeProfiles>
        <activeProfile>setting</activeProfile>
    </activeProfiles>   
</settings>
