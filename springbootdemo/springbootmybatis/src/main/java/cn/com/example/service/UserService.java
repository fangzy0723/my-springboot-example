package cn.com.example.service;


import cn.com.example.domain.Users;

import java.util.List;

/**
 * Created by fangzy on 2018/3/1 16:03
 */
public interface UserService {
    /**
     * 查询所有用户列表
     * @return
     */
    public List<Users> findAll();

    /**
     * 添加一个用户
     * @param user
     */
    public void addUser(Users user);

    /**
     * 根据主键修改用户
     * @param user
     */
    public void updateUser(Users user);

    /**
     * 根据主键删除用户
     * @param id
     */
    public void deleteUser(Long id);
    /**
     * 根据name查询user
     * @param name
     */
    public Users findByName(String name);
}
