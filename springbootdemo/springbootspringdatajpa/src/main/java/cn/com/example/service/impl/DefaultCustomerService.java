package cn.com.example.service.impl;

import cn.com.example.domain.Customer;
import cn.com.example.repository.CustomerRepository;
import cn.com.example.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fangzy on 2018/5/20 17:21
 */
@Service
@Slf4j
public class DefaultCustomerService implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        List<Customer> customers  = customerRepository.findAll();
        log.info("获取全部数据：{}",customers);
        return customers;
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Customer save() {
        return null;
    }

    @Override
    public Customer update() {
        return null;
    }

    @Override
    public void updateById(Integer id) {

    }

    @Override
    public Customer findByName(String name) {
        return null;
    }

    @Override
    public Customer findByNameByQuerySQL(String name) {
        return null;
    }

    @Override
    public Customer findByNameByQueryJPQL(String name) {
        return null;
    }

    @Override
    public List<Customer> findAllByIdSort(Integer id) {
        return null;
    }

    @Override
    public List<Customer> findAllPage(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public List<Customer> findAllPageAndSort(Integer id, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public List<Customer> findByConditions1(Integer id, String name) {
        return null;
    }

    @Override
    public List<Customer> findByConditions2(Integer id, String name) {
        return null;
    }
}
