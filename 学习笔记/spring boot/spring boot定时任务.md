

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9

- ### 方式1：

> 新建一个java类，添加注解@Configuration和@EnableScheduling，开启调度任务。在类中新建一个定时的方法添加注解@Scheduled ，表明该方法是一个调度任务。cron表达式配置定时执行的规则

```
@Configuration
@EnableScheduling
public class SpringBootScheduledController {
    /**
     *每20秒执行一次
     */
    @Scheduled(cron="0/20 * * * * ?")
    public void methodScheduling(){
        System.out.println("methodScheduling定时任务启动了");
    }
}
```


- ### 方法二：

> 在启动类上添加注解@EnableScheduling，开启调度任务。创建一个任务类添加注解@Component，类中方法添加注解@Scheduled，表明该方法是一个调度任务。

```
@SpringBootApplication
@EnableScheduling
public class SpringbootschedulingApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootschedulingApplication.class, args);
	}
}
```
```
/**
 * Created by fangzy on 2018/2/9 17:33
 */
@Component
public class ScheduledController {
    /**
     *每20秒执行一次
     */
    @Scheduled(cron="0/10 * * * * ?")
    public void methodScheduling(){
        System.out.println("methodScheduling定时任务启动了");
    }
}
```

---

- ### 定时规则参考：

> @Scheduled(cron="0/10 * * * * ?")//通过cron表达式定义执行规则， 每隔十秒执行1次
> @Scheduled(fixedRate=1000)//上次开始执行时间点之后1秒再执行                                      
> @Scheduled(fixedDelay=1000)//上次执行完毕时间点之后1秒再执行
> @Scheduled(initialDelay=1000,fixedRate=1000)//第一次延迟1秒后执行，之后按fixedRate的规则每1秒执行1次

