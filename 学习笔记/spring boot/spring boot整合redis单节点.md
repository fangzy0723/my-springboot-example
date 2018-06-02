- #### 在pom文件中添加redis依赖

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
```

- #### 在配置文件中添加配置

```
# REDIS (RedisProperties)
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=localhost
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=1000
```

不同数据类型对应bu'to

到此注入RedisTemplate 或者StringRedisTemplate 即可直接使用

- #### 使用StringRedisTemplate示例

> StringRedisTemplate 继承了 RedisTemplate<String, String> 所以key、value都是字符串类型的，key、value都是使用StringRedisSerializer进行序列化的

```
public StringRedisTemplate() {
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		setKeySerializer(stringSerializer);
		setValueSerializer(stringSerializer);
		setHashKeySerializer(stringSerializer);
		setHashValueSerializer(stringSerializer);
	}
```

StringRedisTemplate 使用示例：

在测试类中注入:
```
    @Autowired
    StringRedisTemplate stringRedisTemplate;
```
测试方法中:
```
    @Test
    public void testStringRedisTemplate() throws Exception {
        stringRedisTemplate.opsForValue().set("age","22");
        System.out.println(stringRedisTemplate.opsForValue().get("age"));
    }
```

RedisTemplate 使用示例：

在测试类中注入:
```
    @Autowired
    RedisTemplate redisTemplate;
```
测试方法中:
```
    @Test
    public void testredisTemplate() throws Exception {
        /*RedisTemplate 需要自己序列化，默认的 JdkSerializationRedisSerializer 存到数据中的是二进制的*/
        redisTemplate.opsForValue().set("aaa",121);
        System.out.println(redisTemplate.opsForValue().get("aaa"));
    }
```

这样存储到redis中的key、value都是被序列化成了二进制的，可自己配置序列化方式，参考如下：

```
@Configuration
public class RedisConfig{

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
```
再次执行上面的测试方法会发现不是二进制的了。

使用RedisTemplate存储对象：

1、首先在pom中添加依赖：
```
		<!--序列化转json的，存入库中的对象可以以json的形式存-->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
		</dependency>
```

2、新建User类实现Serializable：

```
public class User implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    //省略get、set方法，toString方法，构造方法
}
```

3、添加测试方法

```
    @Test
    public void testredisTemplate() throws Exception {
        redisTemplate.opsForValue().set("user",new User(1,"fangzy",20));
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
```

> 存到redis中的数据是json格式的
**{"@class":"cn.com.example.domain.User","id":1,"name":"fangzy","age":20}**
可直接强转User对象
