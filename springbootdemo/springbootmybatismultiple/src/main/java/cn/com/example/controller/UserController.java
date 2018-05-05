package cn.com.example.controller;


import cn.com.example.domain.Users;
import cn.com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by fangzy on 2017/9/2 16:08
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据name查询数据
     * @param name
     * @return
     */
    @GetMapping(value = "/findByName")
    public Users findByName(@RequestParam(name = "name") String name) {
        return userService.findByName(name);
    }

    /**
     * 添加一条数据
     * @param age
     * @param name
     */
    @PostMapping(value = "/userAdd")
    public void userAdd(@RequestParam(name = "age") int age, @RequestParam(name = "name") String name) {
        Users user = new Users();
        user.setAge(age);
        user.setName(name);
        userService.addUser(user);
    }

    /**
     * 根据id修改数据
     * @param age
     * @param name
     * @param id
     */
    @PutMapping(value = "/userUpdate")
    public void userUpdate(@RequestParam(name = "age") int age, @RequestParam(name = "name") String name, @RequestParam(name = "id") Long id) {
        Users user = new Users();
        user.setName(name);
        user.setAge(age);
        user.setId(id);
        user.setBirthday(new Date());
        user.setPassword("111");
        userService.updateUser(user);
    }

    /**
     * 根据id删除数据
     * @param id
     */
    @DeleteMapping(value = "/userDelete")
    public void userDelete(@RequestParam(name = "id",required = false) String id) {
        userService.deleteUser(Long.parseLong(id));
    }

    /**
     * 查询所有数据
     * @return
     */
    @GetMapping(value = "/userFindAll")
    public List<Users> userFindAll() {
        return userService.findAll();
    }

}
