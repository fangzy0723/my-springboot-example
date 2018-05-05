package cn.com.example.repository;


import cn.com.example.domain.Users;

import java.util.List;

/**
 * Created by fangzy on 2018/3/1 17:15
 */
public interface UserMapper {

    /**
     * 根据name查询user
     * @param name
     */
    Users findByName(String name);

    /**
     * 查询所有用户列表
     * @return
     */
    List<Users> findAll();

    /**
     * 添加一个用户
     * @param user
     */
    void addUser(Users user);

    /**
     * 根据主键修改用户
     * @param user
     */
    void updateUser(Users user);

    /**
     * 根据主键删除用户
     * @param id
     */
    void deleteUser(Long id);
}
