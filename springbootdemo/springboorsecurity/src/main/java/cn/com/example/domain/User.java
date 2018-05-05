package cn.com.example.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by fangzy on 2018/4/15 9:17
 */
@Setter
@Getter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer age;
    private String name;
    private Date birthday;

    public User() {
    }

    public User(Integer age, String name, Date birthday) {
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }
}
