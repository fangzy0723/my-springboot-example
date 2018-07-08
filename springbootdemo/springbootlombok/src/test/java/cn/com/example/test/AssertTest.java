package cn.com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * Created by fangzy on 2018/6/16 10:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssertTest {

    @Test
    public void testAssertNotNull() {
        Assert.notNull("ASDA","这个参数不能为null");
    }
    @Test
    public void testAssertIsTrue() {
        Assert.isTrue(false,"这个参数不为true");
    }
    @Test
    public void testAssertHasLength() {
        Assert.hasLength(" ","这个参数不能为空或者null");
    }
    @Test
    public void testAssertHasText() {
        Assert.hasText("","这个参数不能为空或者null");
    }


}
