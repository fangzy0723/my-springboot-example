package cn.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootschedulingApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootschedulingApplication.class, args);
	}
}
