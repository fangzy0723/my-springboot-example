package cn.com.example;

import cn.com.example.domain.Man;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fangzy on 2018/7/7 11:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ManRepositoryTest {

    @Autowired
    ManRepository manRepository;

    @Test
    public void findByName() throws Exception {
        List<Man> manList = manRepository.findByName("hehe");
        manList.stream().forEach(e -> System.out.println(e));
    }

    @Test
    public void insert() throws Exception {
        manRepository.index(new Man("2","sile",20,"中国"));
        manRepository.index(new Man("3","haha",21,"中国"));
        manRepository.index(new Man("4","hehe",22,"中国"));
        manRepository.index(new Man("5","heihie",23,"中国"));
        manRepository.index(new Man("6","nihao",24,"中国"));
        manRepository.index(new Man("7","shaa",25,"中国"));
    }

    @Test
    public void findAll() throws Exception {
        Iterable<Man> manList = manRepository.findAll();
        manList.forEach(e -> System.out.println(e));
    }

}