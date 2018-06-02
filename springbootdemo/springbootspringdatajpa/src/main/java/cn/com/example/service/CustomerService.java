package cn.com.example.service;

import cn.com.example.domain.Customer;

import java.util.List;

/**
 * Created by fangzy on 2018/5/20 17:20
 */
public interface CustomerService {

    /**
     * 调用spring data jpa提供的方法查询全部数据
     * @return
     */
    List<Customer> findAll();

    /**
     * 调用spring data jpa提供的方法查根据主键拆查询
     * @param id
     * @return
     */
    Customer findById(Integer id);
    /**
     * 调用spring data jpa提供的方法进行插入
     */
    Customer save();

    /**
     * 调用spring data jpa提供的方法进行修改
     */
    Customer update();

    /**
     * 调用spring data jpa提供的方法根据id进行删除
     * @param id
     */
    void updateById(Integer id);


    /**
     * 根据spring data jpa 命名规则定义查询方法
     * @param name
     * @return
     */
    Customer findByName(String name);

    /**
     * 根据spring data jpa 命名规则定义查询方法
     * 使用@Query注解 执行SQL查询语句
     * @param name
     * @return
     */
    Customer findByNameByQuerySQL(String name);

    /**
     * 根据spring data jpa 命名规则定义查询方法
     * 使用@Query注解 执行JPQL查询语句
     * @param name
     * @return
     */
    Customer findByNameByQueryJPQL(String name);

    /**
     * 调用spring data jpa提供的根据字段排序
     * @param id 排序字段
     * @return
     */
    List<Customer> findAllByIdSort(Integer id);

    /**
     * 调用spring data jpa提供的分页查询
     * @param page  第几页（从0开始  0：第一页）
     * @param pageSize  每页的条数
     * @return
     */
    List<Customer> findAllPage(Integer page,Integer pageSize);

    /**
     * 调用spring data jpa提供的分页并排序查询
     * @param id 排序字段
     * @param page  第几页（从0开始  0：第一页）
     * @param pageSize  每页的条数
     * @return
     */
    List<Customer> findAllPageAndSort(Integer id,Integer page,Integer pageSize);

    /**
     * 根据条件动态拼查询条件
     * @param id 查询条件
     * @param name 查询条件
     * @return
     */
    List<Customer> findByConditions1(Integer id,String name);

    /**
     * 根据条件动态拼查询条件
     * @param id 查询条件
     * @param name 查询条件
     * @return
     */
    List<Customer> findByConditions2(Integer id,String name);
}
