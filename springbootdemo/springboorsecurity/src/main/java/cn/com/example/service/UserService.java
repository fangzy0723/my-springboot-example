package cn.com.example.service;

import cn.com.example.domain.User;

import java.util.List;

/**
 * Created by fangzy on 2018/4/15 9:21
 */
public interface UserService {
    public void saveUser(User user);
    public List<User> findAllUser();
    public User findUserById(Integer id);
}
