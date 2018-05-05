---
title: Spring boot注解详解
---

- #### @SpringBootApplication

> 申明让spring boot自动给程序进行必要配置，这个配置等于@Configuration， @EnableAutoConfiguration和@ComponentScan三个配置

- #### @ResponseBody

> 该注解修饰的函数，会将结果直接填充到HTTP的响应体中，一般用于构建RESTful Api，该注解一般会配合@RequestMapping一起使用

<!-- more -->

- #### @Controller

> 用于定义控制器类，在spring项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（sevice层），一般这个注解在类中，通常方法需要配合注解@RequestMapping

- #### @RestController

> @ResponseBody和Controller的合集

- #### @RequestMapping

> 提供路径信息，负责URL到Controller中的具体函数的映射

- #### @EnableAutoConfiguration

> Spring boot自动配置（auto-configuration）：尝试根据你添加的jar依赖自动配置你的spring应用。例如：如果你的classpath下存在的HSQLDB，并且你没有手动配置任何数据库连接beans，那么我们将自动配置一个内存型（in-memory）数据库。你可以将@EnableAutoConfiguration或者@SpringBootApplication注解添加到一个@Configuration类上来选择自动配置。如果发现应用了你不想要的特定自动配置类，你可以使用@EnableAutoConfiguration注解的排除属性来禁用它们

- #### @ComponentScan

> 表示将该类自动发现（扫描）并注册到bean，可以自动收集所有的spring组件，包括@Configuration类。我们经常使用@ComponentScan注解扫描搜索beans，并结合@Autowired注解导入。如果没有配置的话，spring boot会扫描启动类所在包下以及子包下的使用@Service，@Repository等注解的类

- #### @Configuration

> 相当于传统的xml配置文件，如果有些第三方库需要用到的xml文件，建议仍然通过 @Configuration类作为项目的配置主类可以使用@ImportResource注解加载xml配置文件

- #### @Import

> 用来导入其它配置类

- #### @ImportResource

> 用来加载xml配置文件

- #### @Autowired

> 自动导入依赖的bean

- #### @Service

> 一般用于修饰service层的组件

- #### @Repository

> 使用@Repository注解可以确保DAO或者repositories提供异常转译，这个注解修饰的DAO或者repositories类会被ComponetScan发现并配置，同时也不需要为它们提供xml配置项

- #### @Bean

> 用@Bean标注方法等价于xml配置的bean

- #### @Value

> 注入spring boot application.properties配置的属性的值
```
实例代码：

@Value(value = “#{message}”)
private String message
```
- #### @Qualifier

> @Qualifier限定描述符除了能根据名字进行注入，但能进行更细力度的控制如何选择候选者，具体使用方法如下：
```
@Autowired
@Qualifier（value = “demoInfoservice”）
private DemoInfoService demoInfoservice;
```
- #### @Inject

> 等价于默认的@Autowired，只是没有required属性

