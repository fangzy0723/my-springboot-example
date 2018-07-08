package cn.com.example;

import cn.com.example.domain.Man;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by fangzy on 2018/7/7 11:54
 */
public interface ManRepository extends ElasticsearchRepository<Man,String> {
    List<Man> findByName(String name);
}
