package cn.com.example.service;

import cn.com.example.domain.User;
import cn.com.example.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fangzy on 2018/4/15 9:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUser() throws Exception {
        userService.saveUser(new User(10,"asd",new Date()));
    }

    @Test
    public void findAllUser() throws Exception {
        List<User> userList = userService.findAllUser();
        System.out.println(userList.toString());
    }

    @Test
    public void findUserById() throws Exception {
        User user = userService.findUserById(16);
        System.out.println(user.toString());
    }

}