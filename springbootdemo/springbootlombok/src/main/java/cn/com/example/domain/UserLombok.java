package cn.com.example.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by fangzy on 2018/4/13 9:52
 */
@Data
public class UserLombok  implements Serializable{

    private String name;
    private Integer age;
    private String sex;
}
