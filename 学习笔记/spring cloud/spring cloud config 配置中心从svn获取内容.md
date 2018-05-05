spring cloud config配置中心分两部分:server端和client端；

> server端读取远程仓库配置文件，提供Http rest Api共client端调用。

> client端调用server端提供的接口获取配置文件信息。

- #### server端使用参考：
	##### 1、pom文件引入依赖如下
        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.tmatesoft.svnkit</groupId>
			<artifactId>svnkit</artifactId>
		</dependency>
	##### 2、配置文件
		server.port=8081
		spring.application.name=spring-config-server

		spring.cloud.config.server.svn.uri=http://127.0.0.1:8080/svn/repository/ESERVICE/config-repo
		spring.cloud.config.server.svn.username= vdr_fangzhiyong
		spring.cloud.config.server.svn.password= Ab123456
		spring.cloud.config.server.default-label=
		spring.profiles.active=subversion
	##### 3、激活对配置中心的支持
		在配置类上添加注解@EnableConfigServer
	##### 4、测试地址
		http://localhost:8081/spring-config/test
- #### client端：
	##### 1、pom文件引入依赖如下
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
	##### 2、配置文件
		需要配置两个配置文件，application.properties和bootstrap.properties
		application.properties如下：
			server.port=8082
			spring.application.name=spring-config-client

		bootstrap.properties如下：
			spring.cloud.config.name=spring-config
			spring.cloud.config.profile=test
			spring.cloud.config.uri=http://localhost:8081
	##### 3、测试类：
		@RestController
		@RefreshScope
		public class ConfigController {

			@Value("${config.hello}")
			private String configVal;

			@RequestMapping("/getConfig")
			public String getConfig(){
				return configVal;
			}
		}
	##### 4、测试地址
		http://localhost:8082/getConfig
- #### SVN部分：
	>	在ESERVICE目录下新建文件夹config-repo，在config-repo文件夹下新建配置文件、根据不同的环境新建**spring-config-dev.properties**、**spring-config-pro.properties**、**spring-config-test.properties**
		在配置文件中添加内容用于测试： 
```
config.hello=config dev properties
```
添加的内容需要提交到远程仓库
