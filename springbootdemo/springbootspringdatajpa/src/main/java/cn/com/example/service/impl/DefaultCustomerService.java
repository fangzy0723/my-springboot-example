package cn.com.example.service.impl;

import cn.com.example.domain.Customer;
import cn.com.example.repository.CustomerRepository;
import cn.com.example.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

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
        Optional<Customer> customer = customerRepository.findById(id);
        Customer customer1 = customer.get();
        log.info("customer1:{}",customer1);
        return customer1;
    }

    @Override
    public Customer save() {
        Customer customer = new Customer();
        customer.setName("你好");
        customer = customerRepository.save(customer);
        log.info("customer:{}",customer);
        return customer;
    }

    @Override
    public Customer update(Integer id) {
        Customer customer = new Customer();
        //id 存在修改，不存在插入
        customer.setId(id);
        customer.setName("nihao");
        customer = customerRepository.save(customer);
        log.info("customer:{}",customer);
        return customer;
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customer = customerRepository.findByName(name);
        log.info("customer:{}",customer);
        return customer;
    }

    @Override
    public List<Customer> findByNameByQuerySQL(String name) {
        List<Customer> customer = customerRepository.findByNameByQuerySQL(name);
        log.info("customer:{}",customer);
        return customer;
    }

    @Override
    public List<Customer> findByNameByQueryJPQL(String name) {
        List<Customer> customer = customerRepository.findByNameByQueryJPQL(name);
        log.info("customer:{}",customer);
        return customer;
    }

    @Override
    public List<Customer> findAllByIdSort(String id) {
        //不输排序方式默认是升序
        Sort sort = Sort.by(Sort.Direction.DESC,id);
        List<Customer> customer = customerRepository.findAll(sort);
        log.info("customer:{}",customer);
        return customer;
    }

    @Override
    public List<Customer> findAllPage(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<Customer> customer = customerRepository.findAll(pageable);
        log.info("customer:{}",customer.getContent());
        return customer.getContent();
    }

    @Override
    public List<Customer> findAllPageAndSort(String id, Integer page, Integer pageSize) {
        //不输排序方式默认是升序
        Sort sort = Sort.by(Sort.Direction.DESC,id);
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<Customer> customer = customerRepository.findAll(pageable);
        log.info("customer:{}",customer.getContent());
        return customer.getContent();
    }

    @Override
    public List<Customer> findByConditions1(Integer id, String name) {
        Specification specification = new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                //查询 id大>3 and name=zhangsan
                Predicate predicate_id = criteriaBuilder.gt(root.get("id"),id);
                Predicate predicate_name = criteriaBuilder.equal(root.get("name"),name);
                Predicate predicate = criteriaBuilder.and(predicate_id,predicate_name);
                return predicate;
            }
        };
        List<Customer> customers = customerRepository.findAll(specification);
        log.info("customer:{}",customers);
        return customers;
    }

    @Override
    public List<Customer> findByConditions2(Integer id, String name) {

        Specification specification = new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                //查询 id大>3 and name=zhangsan
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.gt(root.get("id"), id));
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Customer> customers = customerRepository.findAll(specification);
        log.info("customer:{}",customers);
        return customers;
    }

    /**
     * 参考方法
     */
//    private Specification searchCriteria(List<String> orgnIdsList, String name, String staff_id, String comYears,
//                                         JSONArray title, JSONArray groups, JSONArray branch) {
//        Specification querySpecifi = new Specification<Staff>() {
//            @Override
//            public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//
//                /**
//                 * 姓名
//                 */
//                List<Predicate> predicates = new ArrayList<>();
//                if(null != name && !"".equals(name)){
//                    predicates.add(criteriaBuilder.equal(root.get("name"), name));
//
//                }
//                /**
//                 * 员工号
//                 */
//                if(null != staff_id && !"".equals(staff_id)){
//                    predicates.add(criteriaBuilder.equal(root.get("employeeBumber"), staff_id));
//                }
//                /**
//                 * 工龄
//                 */
//                if(null != comYears && !"".equals(comYears)){
//                    Calendar calendar= Calendar.getInstance();
//                    calendar.setTime(new Date());
//                    calendar.add(Calendar.YEAR,-Integer.parseInt(comYears));
//                    predicates.add(criteriaBuilder.lessThan(root.get("entryDate"),calendar.getTime()));
//                }
//                /**
//                 * 岗位
//                 */
//                if(null != title && title.size()>0){
//                    predicates.add(root.get("post").in(title));
//                }
//                /**
//                 * 群组
//                 */
//                if(null != groups && groups.size()>0){
//                    predicates.add(root.get("branchGroup").in(groups));
//                    predicates.add(root.get("postGroup").in(groups));
//                }
//                /**
//                 * 分公司
//                 */
//                if(null != branch && branch.size()>0){
//                    //根据分公司查询时，既要满足选中的分公司且还得满足是当前登陆人所在机构或者子机构的
//                    Predicate p1 = root.get("branch").in(branch);
//                    Predicate p2 = root.get("branch").in(orgnIdsList);
//                    predicates.add(criteriaBuilder.and(p1,p2));
//                }else{
//                    //查询的时候分公司没传值，则查询的机构或者子机构的code从session中获取
//                    Predicate p1 = root.get("branch").in(orgnIdsList);
//                    Predicate p2 = root.get("cso").in(orgnIdsList);
//                    predicates.add(criteriaBuilder.or(p1,p2));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        };
//        return querySpecifi;
//    }
}
