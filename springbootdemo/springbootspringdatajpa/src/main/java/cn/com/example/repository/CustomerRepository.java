package cn.com.example.repository;

import cn.com.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by fangzy on 2018/5/20 17:17
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer>,JpaSpecificationExecutor<Customer> {
    List<Customer> findByName(String name);

    /**
     * 使用原生sql查询
     * @param name
     * @return
     */
    @Query(value = "select * from customer where name = :username",nativeQuery = true)
    List<Customer> findByNameByQuerySQL(@Param("username") String name);

    /**
     * 使用jpql查询
     * @param name
     * @return
     */
    @Query(value = "select c from Customer c where c.name = ?1")
    List<Customer> findByNameByQueryJPQL(String name);
}
