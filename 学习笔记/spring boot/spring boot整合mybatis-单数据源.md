

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9

**参考示例代码：**
> 首先在启动类上添加 mapper 接口类扫描包配置注解
@MapperScan("repository的接口包路径")


- #### controller

```
/**
 * Created by fangzy on 2017/9/2 16:08
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据name查询数据
     * @param name
     * @return
     */
    @GetMapping(value = "/findByName")
    public Users findByName(@RequestParam(name = "name") String name) {
        return userService.findByName(name);
    }

	
    /**
     * 添加一条数据
     * @param age
     * @param name
     */
    @PostMapping(value = "/userAdd")
    public void userAdd(@RequestParam(name = "age") int age, @RequestParam(name = "name") String name) {
        Users user = new Users();
        user.setAge(age);
        user.setName(name);
        userService.addUser(user);
    }

    /**
     * 根据id修改数据
     * @param age
     * @param name
     * @param id
     */
    @PutMapping(value = "/userUpdate")
    public void userUpdate(@RequestParam(name = "age") int age, @RequestParam(name = "name") String name, @RequestParam(name = "id") Long id) {
        Users user = new Users();
        user.setName(name);
        user.setAge(age);
        user.setId(id);
        user.setBirthday(new Date());
        user.setPassword("111");
        userService.updateUser(user);
    }

    /**
     * 根据id删除数据
     * @param id
     */
    @DeleteMapping(value = "/userDelete")
    public void userDelete(@RequestParam(name = "id",required = false) String id) {
        userService.deleteUser(Long.parseLong(id));
    }

    /**
     * 查询所有数据
     * @return
     */
    @GetMapping(value = "/userFindAll")
    public List<Users> userFindAll() {
        return userService.findAll();
    }

}
```

- #### server

```
/**
 * Created by fangzy on 2018/3/1 16:03
 */
public interface UserService {
    /**
     * 查询所有用户列表
     * @return
     */
    public List<Users> findAll();

    /**
     * 添加一个用户
     * @param user
     */
    public void addUser(Users user);

    /**
     * 根据主键修改用户
     * @param user
     */
    public void updateUser(Users user);

    /**
     * 根据主键删除用户
     * @param id
     */
    public void deleteUser(Long id);
    /**
     * 根据name查询user
     * @param name
     */
    public Users findByName(String name);
}
```
```
/**
 * Created by fangzy on 2018/3/1 16:06
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Users> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void addUser(Users user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(Users user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public Users findByName(String name) {
        return userMapper.findByName(name);
    }

}
```


- #### repository

```
/**
 * Created by fangzy on 2018/3/1 17:15
 */
public interface UserMapper {

    /**
     * 根据name查询user
     * @param name
     */
    Users findByName(String name);

    /**
     * 查询所有用户列表
     * @return
     */
    List<Users> findAll();

    /**
     * 添加一个用户
     * @param user
     */
    void addUser(Users user);

    /**
     * 根据主键修改用户
     * @param user
     */
    void updateUser(Users user);

    /**
     * 根据主键删除用户
     * @param id
     */
    void deleteUser(Long id);
}
```

- #### domain

```
/**
 * Created by fangzy on 2017/9/2 15:52
 */
public class Users implements Serializable {

    private Long id;

    private Integer age;

    private String name;

    private String password;

    private Date birthday;

    //省略get、set方法
}
```

- #### xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.com.example.repository.UserMapper">
<!--namespace的值是mapper接口文件的路径-->
	<resultMap id="BaseResultMap" type="cn.com.example.domain.Users">
		<id column="id" property="id" />
		<result column="age" property="age" />
		<result column="name" property="name" />
		<result column="password" property="password" />
		<result column="birthday" property="birthday" />
	</resultMap>

	<parameterMap id="User" type="cn.com.example.domain.Users"/>

	<sql id="Base_Column_List">
		id, age, name,password,birthday
	</sql>
	<!--根据name查询数据-->
	<select id="findByName" resultMap="BaseResultMap" parameterType="java.lang.String">
		select
		<include refid="Base_Column_List" />
		from user
		where name = #{name}
	</select>
	<!--查询所有数据-->
	<select id="findAll" resultMap="BaseResultMap">
		SELECT
		<include refid="Base_Column_List" />
		FROM user
	</select>
	<!--根据id删除数据-->
	<delete id="deleteUser"  parameterType="java.lang.Long">
		DELETE FROM user WHERE id = #{id}
	</delete>
	<!--根据主键修改数据-->
	<update id="updateUser" parameterMap="User" >
		update user
		<set>
			<if test="age != null">
				age=#{age},
			</if>
			<if test="name != '' and name != null">
				name=#{name},
			</if>
			<if test="password != '' and password != null">
				password=#{password},
			</if>
			<if test="birthday != null">
				birthday=#{birthday},
			</if>
		</set>
		<where>
			<if test="id != null">
				id = #{id}
			</if>
		</where>
	</update>
	<!--插入数据-->
	<insert id="addUser" parameterMap="User">
		insert into user(age,name) values(#{age},#{name})
	</insert>

</mapper>

```

- #### properties

```
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/my_db?useUnicode = true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
spring.datasource.username=root
spring.datasource.password=123456

#判断数据库是否连接成功
spring.datasource.validationQuery=SELECT '1' FROM DUAL

## Mybatis 配置
#domain的路径
mybatis.typeAliasesPackage=cn.com.example.domain
#xml的路径
mybatis.mapperLocations=classpath:mapper/*.xml
```

- #### pom

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>cn.com.example</groupId>
	<artifactId>springbootmybatis</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>springbootmybatis</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.10.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.1</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>


</project>
```
