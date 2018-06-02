package cn.com.example.repository;

import cn.com.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fangzy on 2018/5/20 17:17
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
