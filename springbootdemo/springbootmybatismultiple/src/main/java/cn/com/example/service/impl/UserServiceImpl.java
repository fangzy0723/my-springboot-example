package cn.com.example.service.impl;

import cn.com.example.domain.Users;
import cn.com.example.repository.UserMapper;
import cn.com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
