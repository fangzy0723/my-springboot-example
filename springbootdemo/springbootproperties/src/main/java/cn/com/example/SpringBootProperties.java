package cn.com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzy on 2018/2/5 10:31
 */
@Configuration
@ConfigurationProperties(prefix = "springbootdemo")
public class SpringBootProperties {
    private UserPropertiesConfig user = new UserPropertiesConfig();

    public UserPropertiesConfig getUser() {
        return user;
    }

    public void setUser(UserPropertiesConfig user) {
        this.user = user;
    }
}
