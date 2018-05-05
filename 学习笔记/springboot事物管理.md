

> Spring Boot 默认集成事务，所以无须手动开启。 使用 @EnableTransactionManagement 注解，就可以用 @Transactional注解进行事务管理。使用了 @EnableTransactionManagement 后，Spring 会自动扫描注解 @Transactional 的方法和类。

**启动类增加@EnableTransactionManagement注解，类或者方法上添加@Transactional注解即可。**

```
例如：@Transactional（value = "transactionManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED,rollbackFor = Exception.class,timeout=36000）
括号中的参数都是可选的，可不填
```

noRollbackFor 修饰表明不做事务回滚,rollbackFor 修饰的表明需要事务回滚

默认情况下只有RuntimeException异常才回滚,为了让Exception也回滚,需增加 rollbackFor = Exception.class.

@Rollback标签是需要配合@Transcational标签共同使用的，加了Transactional标签的方法，会默认视为@Rollback(true)，如果让这个方法不回滚，那么就要手动设置@Rollback(false)


- ### 事物特性:

> 原子性、隔离性、一致性、持久性

spring 事物管理高层抽象出三个接口:

1、PlatfromTransactionManagement    事物管理器

2、TransactionDefinition  事物定义信息：隔离级别、传播特性、超时、只读

3、TransactionStatus   事物具体运行状态


> spring为不同的持久层提供了不同的PlatfromTransactionManagement接口实现类

> spring jdbc或者mybatis --->> **.jdbc.datasource.DataSourceTransactionManagement
> hibernate3 --->> **.orm.hibernate3.HibernateTransactionManagement
> jpa --->> **.orm.jpa.JpaTransactionManagement

> 我们如果使用到 spring-boot-starter-jdbc 、 spring-boot-starter-data-jpa。Spring Boot 会自动默认分别注入DataSourceTransactionManager， JpaTransactionManager

使用 @Transactional 注解在方法或类上表明这个方法或类需要事务支持，此时，Spring 拦截器会在这个方法调用时，开启一个新的事务，当方法运行结束且无异常的情况下，提交这个事务。

注解@EnableTransactionManagement和@Transactional来自于spring-tx.jar这个包，在引入mybatis依赖时，已自动引入，所以只要把mybatis-spring-boot-starter依赖引入进来，那事务就不需要再引入包了。

- ### 隔离级别:

> 隔离级别是指若干个并发的事务之间的隔离程度，与我们开发时候主要相关的场景包括：脏读取、重复读、幻读。

我们可以看org.springframework.transaction.annotation.Isolation枚举类中定义了五个表示隔离级别的值：

```
public enum Isolation {

DEFAULT(-1),

READ_UNCOMMITTED(1),

READ_COMMITTED(2),

REPEATABLE_READ(4),

SERIALIZABLE(8);

}
```

DEFAULT  (DEFAULT)：这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是：READ_COMMITTED。

READ_UNCOMMITTED  (READ_UNCOMMITED)：该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读和不可重复读，因此很少使用该隔离级别。

READ_COMMITTED  (READ_COMMITED)：该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。

REPEATABLE_READ  (REPEATABLE_READ)：该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。即使在多次查询之间有新增的数据满足该查询，这些新增的记录也会被忽略。该级别可以防止脏读和不可重复读。

SERIALIZABLE  (SERIALIZABLE)：所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

> 通过使用isolation属性设置，例如：

```
@Transactional(isolation = Isolation.DEFAULT)
```

- ### 传播行为:

> 所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为。

我们可以看org.springframework.transaction.annotation.Propagation枚举类中定义了6个表示传播行为的枚举值：

```
public enum Propagation {

    REQUIRED(0),

    SUPPORTS(1),

    MANDATORY(2),

    REQUIRES_NEW(3),

    NOT_SUPPORTED(4),

    NEVER(5),

    NESTED(6);

}
```

REQUIRED （PROPAGATION_REQUIRED）：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。

SUPPORTS （PROPAGATION_SUPPORTS）：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。

MANDATORY （PROPAGATION_MANDATORY）：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。

REQUIRES_NEW （PROPAGATION_REQUIRES_NEW）：创建一个新的事务，如果当前存在事务，则把当前事务挂起。

NOT_SUPPORTED （PROPAGATION_NOT_SUPPORTED）：以非事务方式运行，如果当前存在事务，则把当前事务挂起。

NEVER （PROPAGATION_NEVER）：以非事务方式运行，如果当前存在事务，则抛出异常。

NESTED （PROPAGATION_NESTED）：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于REQUIRED。

> 通过使用propagation属性设置，例如：

```
@Transactional(propagation = Propagation.REQUIRED)
```