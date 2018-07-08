package com.example.controller;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by fangzy on 2018/6/23 18:40
 * 对流操作的步骤
 * 1、创建流
 * 2、中间操作
 * 3、终止操作
 */
public class TestStreamApi {

    List<User> userList = Arrays.asList(
            new User(20,"zhangsan",78),
            new User(18,"lisi",56),
            new User(10,"wangwu",100),
            new User(39,"maliu",28),
            new User(26,"lisa",89),
            new User(20,"soni",90)
    );

    /**
     * 创建流的方式
     */
    @Test
    public void testStreamApi1(){

    }

    /**
     * 中间操作
     * 筛选与切片
     * filter-接收Lambda表达式，从流中过滤掉某些元素
     * limit(n)-截断流，使其元素不超过指定个数，取前多少个
     * skip(n)-忽略前 n 个元素的流，若流中元素个数不超过 n 个，则返回一个空流。与limit互补
     * distinct-去重，通过流所生成元素的 hashCode()和 equals() 去除重复元素,需要重写对象的hashCode()和 equals()的方法
     */
    @Test
    public void testStreamApi2(){
        //过滤出用户年龄大于等于20的
        userList.stream().filter((e) -> e.getAge()>=20).forEach(System.out::println);
        System.out.println("----------");
        //过滤出用户年龄大于等于20的，取前两个
        userList.stream().filter(e -> e.getAge()>=20).limit(2).forEach(System.out::println);
        System.out.println("----------");
        //过滤出用户年龄大于等于20的，忽略掉前两个
        userList.stream().filter(e -> e.getAge()>=20).skip(2).forEach(System.out::println);
        System.out.println("----------");
        //过滤出用户年龄大于等于20的，去除重复数据
        userList.stream().filter(e -> e.getAge()>=20).distinct().forEach(System.out::println);
    }

    /**
     * 中间操作
     *  映射
     *  map-接收Lambda表达式，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     *  flatMap-接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
     *  map、flatMap 可以类比成集合中的add、addAll
     */
    @Test
    public void testStreamApi3(){
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream().map(e -> e.toUpperCase()).forEach(System.out::println);
        System.out.println("-----------");
        //只获取用户的name
        userList.stream().map(User::getName).forEach(System.out::println);
    }

    /**
     * 中间操作
     * 排序
     *  sorted()-自然排序（Comparable）
     *  sorted(Comparator com)-定制排序（Comparator）
     */
    @Test
    public void testStreamApi4(){
        List<String> list = Arrays.asList("aaa","ddd","bbb","ccc");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("-------------");
        userList.stream().sorted((e1,e2) -> {
            //年龄相同用name升序，年龄不同用年龄升序
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }

    /**
     * 终止操作
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否至少匹配一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回流中任意元素
     * count-返回流中元素个数
     * max-返回流中最大值
     * min-返回流中最小值
     */

    @Test
    public void testStreamApi5(){
        //所有人的name是否都是张三
        boolean b1 = userList.stream().allMatch(e -> e.getName().equals("zhangsan"));
        System.out.println("b1:" + b1);
        //所有人中是否有name为zhangsan的
        boolean b2 = userList.stream().anyMatch(e -> e.getName().equals("zhangsan"));
        System.out.println("b2:" + b2);
        //是否所有人的name都是zhangsan
        boolean b3 = userList.stream().noneMatch(e -> e.getName().equals("zhangsan"));
        System.out.println("b3:" + b3);
        //获取年龄最大的第一个元素
        Optional<User> op1 = userList.stream().sorted((e1, e2) -> -Integer.compare(e1.getAge(),e2.getAge())).findFirst();
        System.out.println("op1:"+op1.get());
        //获取年龄为20的
        //stream 串行流只有一个线程去找，返回的都是第一个匹配的
        Optional<User> op2 = userList.stream().filter(e -> e.getAge() == 20).findAny();
        //并行流 多个线程去找，谁先找到返回谁的
//        Optional<User> op2 = userList.parallelStream().filter(e -> e.getAge() == 20).findAny();
        System.out.println("op2:"+op2.get());
        //获取元素总个数
        long l1 = userList.stream().count();
        System.out.println("l1:"+l1);
        //获取分数最大的人
        Optional<User> op3 = userList.stream().max((e1,e2) -> e1.getFenshu().compareTo(e2.getFenshu()));
        System.out.println("op3:"+op3.get());
        //获取分数最小值
        Optional<Integer> op4 = userList.stream().map(User::getFenshu).min(Integer::compare);
        System.out.println("op4:"+op4.get());
    }

    /**
     * 归约
     * reduce(T indentity,BinaryOprator) / reduce(BinaryOprator) -可以将流中的元素反复结合起来，得到一个指
     */
    @Test
    public void testStreamApi6(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0,(x,y) -> x+y);
        System.out.println("list元素求和："+sum);
        //获取所有用户的总分数
        Optional<Integer> op1 = userList.stream().map(User::getFenshu).reduce(Integer::sum);
        System.out.println("所有用户总分数之和："+op1);

    }

    /**
     * 收集
     * collect-将流转成其他形式，接收一个Collector接口的实现，用户给stream中元素做汇总的方法
     * Collectors提供了很多静态的收集器实例
     */
    @Test
    public void testStreamApi7(){
        //收集所有用户的name放到list中
        List<String> nameList = userList.stream().map(User::getName).collect(Collectors.toList());
        System.out.println("所有用户的name:"+nameList);

        //获取平均年龄
        Double age = userList.stream().collect(Collectors.averagingDouble(User::getAge));
        System.out.println("所有用户的平均年龄："+age);

        //获取最大年龄的用户
        Optional<User> users = userList.stream().collect(Collectors.maxBy((e1,e2) -> e1.getAge().compareTo(e2.getAge()) ));
        System.out.println("最大年龄的用户："+users.get());

        //根据name进行分组
        Map<String,List<User>> mapuser =  userList.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println("根据name分组用户："+mapuser);

        //根据年龄是否大于20分区
        Map<Boolean,List<User>> mapUsers = userList.stream().collect(Collectors.partitioningBy(e -> e.getAge()>20));
        System.out.println("根据年龄是否大于20分区："+mapUsers);

        //获取多有name，字符串拼接
        String names = userList.stream().map(User::getName).collect(Collectors.joining(","));
        System.out.println("获取多有name，字符串拼接："+names);

    }

    class User {

        private Integer age;
        private String name;
        private Integer Fenshu;

        public User() {
        }

        public User(Integer age) {
            this.age = age;
        }

        public User(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public User(Integer age, String name, Integer fenshu) {
            this.age = age;
            this.name = name;
            Fenshu = fenshu;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (!age.equals(user.age)) return false;
            if (!name.equals(user.name)) return false;
            return Fenshu.equals(user.Fenshu);
        }

        @Override
        public int hashCode() {
            int result = age.hashCode();
            result = 31 * result + name.hashCode();
            result = 31 * result + Fenshu.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", Fenshu=" + Fenshu +
                    '}';
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getFenshu() {
            return Fenshu;
        }

        public void setFenshu(Integer fenshu) {
            Fenshu = fenshu;
        }
    }
}
