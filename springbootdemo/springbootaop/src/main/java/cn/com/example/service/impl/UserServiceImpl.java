package cn.com.example.service.impl;

import cn.com.example.domain.User;
import cn.com.example.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by fangzy on 2018/2/9 16:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        User user = new User();
        user.setName("lisa");
        return user;
    }
}
