package cn.com.example.repository;

import cn.com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fangzy on 2018/4/15 9:19
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
