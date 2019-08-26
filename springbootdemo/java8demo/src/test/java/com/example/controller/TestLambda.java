package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Created by fangzy on 2018/6/23 10:27
 * Lambda 表达式需要函数式接口的支持
 * 接口中只有一个抽象方法的接口是函数式接口 使 用@FunctionalInterface修饰 可以检查是否是函数式接口
 */
//@SpringBootTest
public class TestLambda {

     /**
      * Lambda 读取文件
      */
    @Test
    public void testLambda0(){

        //读取文件，每次读取一行，把非空行放到list中
        List<String> collect = Files.lines(Paths.get("C:\\Users\\hdbx\\Desktop\\aa")).filter(e -> !StringUtils.isEmpty(e)).collect(Collectors.toList());
        System.out.println(collect);

    }

    /**
     * Lambda 表达式语法一：无参数无返回值
     */
    @Test
    public void testLambda1(){

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 1");
            }
        };
        r1.run();
        System.out.println("===============");


        Runnable r2 = () -> System.out.println("Hello World 2");
        r2.run();

    }


    /**
     * Lambda 表达式语法二：有参数无返回值
     */
    @Test
    public void testLambda2(){
        Consumer consumer = (t) -> System.out.println(t);
        consumer.accept("有参数无返回值语法1");

        Consumer consumer1  = t -> System.out.println(t);
        consumer1.accept("有参数无返回值语法2");

    }

    /**
     * Lambda 表达式语法三：有两个及以上参数，Lambda有多条语句 有返回值
     */
    @Test
    public void testLambda3(){
        /**
         * t1 < t2 返回-1
         * t1 = t2 返回0
         * t1 > t2 返回1
         *
         */
        Comparator<Integer> comparator = (t1,t2) -> {
            System.out.println("两个及以上参数，Lambda有多条语句");
            return Integer.compare(t1,t2);
        };
        System.out.println(comparator.compare(2, 2));
    }

    /**
     * Lambda 表达式语法四：有两个及以上参数，只有一条语句  且有返回值
     * {} 和 return 都可以省略不写
     */
    @Test
    public void testLambda4(){
        /**
         * t1 < t2 返回-1
         * t1 = t2 返回0
         * t1 > t2 返回1
         *
         */
        Comparator<Integer> comparator = (t1,t2) -> Integer.compare(t1,t2);
        System.out.println(comparator.compare(2, 2));
    }

    /**
     * 使用Collections中sort排序方法，使用自定义的排序算法对于集合中的对象的排序
     * 当年龄相同时使用name升序排序，年龄不同时使用年龄升序排序
     */
    @Test
    public void testLambda5(){
        List<User> userList = Arrays.asList(
                new User(20,"zhangsan",78),
                new User(18,"lisi",56),
                new User(10,"wangwu",100),
                new User(39,"maliu",28),
                new User(26,"lisa",89),
                new User(20,"soni",90)
        );

        Collections.sort(userList,(t1,t2) -> {
            if(t1.getAge()==t2.getAge()){
                return t1.getName().compareTo(t2.getName());
            }else{
                return Integer.compare(t1.getAge(),t2.getAge());
            }
        });
        userList.forEach(System.out::println);
    }

    /**
     * 定义一个函数式接口MyFunctionInterface，接口中定义一个函数实现如下需求
     * 1、将参数转成大写并返回
     * 2、将传入的参数转成大写并截取2-5位返回
     */
    @Test
    public void lambdaTest6(){
        //将参数转成大写并返回
        System.out.println(getVal("fangzhiyong", t -> t.toUpperCase()));
        //将传入的参数转成大写并截取2-5位返回
        System.out.println(getVal("fangzhiyong", t -> t.toUpperCase().substring(2, 5)));

    }

    /**
     * 使用接口MyFunctionInterface中的函数对字符串进行处理
     * @return
     */
    public String getVal(String str,MyFunctionInterface fun){
        return fun.getVal(str);
    }


    /**
     * JAVA8 内置的四大核心函数式接口之Consumer
     * 接口的方法是有参数没有返回值
     * Consumer<T> : 消费型接口
     * void accept(T t);
     */
    @Test
    public void lambdaTest7(){
        lambdaTest7("fangzy",t -> System.out.println("my name is :"+t));
    }

    public void lambdaTest7(String str,Consumer<String> consumer){
        consumer.accept(str);
    }

    /**
     * JAVA8 内置的四大核心函数式接口之Supplier
     * Supplier接口的方法是没有参数有返回值
     * Supplier<T> : 供给型接口
     * T get();
     */
    @Test
    public void lambdaTest8(){
        List<String> list = lambdaTest8(10,() -> UUID.randomUUID().toString());
        list.forEach(System.out::println);
    }
    /**
     * 返回指定个数的集合
     * @param num
     * @param supplier
     * @return
     */
    public List<String> lambdaTest8(Integer num,Supplier<String> supplier){

        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * JAVA8 内置的四大核心函数式接口之Function
     * Function接口的方法是有参数有返回值
     * Function<T,R> : 函数型接口
     * R apply(T t);
     */
    @Test
    public void lambdaTest9(){
        String str = lambdaTest9("fangzhiyong",(t) -> "my name is "+t);
        System.out.println(str);
    }
    public String lambdaTest9(String str,Function<String,String> function){
        return function.apply(str);
    }

    /**
     * JAVA8 内置的四大核心函数式接口之Predicate
     * Predicate<T> : 断言型接口
     * boolean test(T t);
     */
    @Test
    public void lambdaTest10(){

        List<String> list = Arrays.asList("zhangsan","lisi","ok","nihao","haha");
        //获取集合中元素长度大于4的
        list = lambdaTest10(list,(t) -> t.length()>4);
        list.forEach(System.out::println);
    }
    public List<String> lambdaTest10(List<String> list,Predicate<String> predicate){
        List<String> strList = new ArrayList<>();
        for (String str :list) {
            if(predicate.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

    /**
     * 方法引用：
     * 若Lambda表达式体中的内容有方法已经实现了，我们可以使用方法引用
     * 可以理解为方法引用是Lambda表达的另外一种表现形式
     * 主要有三种语法格式
     * 对象::实例方法名
     * 类::静态方法名
     * 类::实力方法名
     * 方法引用注意的地方：
     * ① Lambda体中调用方法的参数列表和返回值类型要与函数式接口中抽象方法的参数列表和返回值类型要一致
     * ② 若Lambda表达式参数列表中的第一个参数是实例方法的调用者，而第二个参数是实力方法的参数时可以使用 类名::实例方法名（String::equals）
     */
    /**
     * 对象::实例方法名的用法
     */
    @Test
    public void lambdaTest11(){

        PrintStream ps = System.out;
        //println方法的参数列表和返回值类型和Consumer接口中的accept方法一直，可以使用方法引用的形式
        Consumer<String> con1 = ps::println;
        con1.accept("输出内容");
        User user = new User();
        //getAge方法的参数列表和返回值类型和Supplier接口中的get方法一直，可以使用方法引用的形式
        Supplier<Integer> sup = user::getAge;
        System.out.println("age:"+sup.get());

    }

    /**
     * 类::静态方法名
     */
    @Test
    public void lambdaTest12(){
        //compare方法的参数列表和返回值类型和Comparator接口中的compare方法一直，可以使用方法引用的形式
        Comparator<Integer> com = Integer::compare;
        System.out.println("两个参数比较返回结果："+com.compare(2, 1));

    }

    /**
     * 类::实例方法名
     * 参数列表中的第一个参数是实例方法的调用者，而第二个参数是实力方法的参数时可以使用
     */
    @Test
    public void lambdaTest13(){
        BiPredicate<String,String> bi = String::equals;
        System.out.println("两个参数equals比较的结果："+bi.test("aa","aa1"));

    }

    /**
     * 构造器引用
     * 使用注意：需要调用的构造器的参数列表要和函数式接口中抽象方法的参数列表保持一致
     */
    @Test
    public void lambdaTest14(){

        //调用的是无参的构造函数
        Supplier<User> sup = User::new;
        User userSup = sup.get();
        System.out.println(userSup);

        //调用的是只有一个Integer类型的构造函数
        Function<Integer,User> fun1 = User::new;
        User userFun1 = fun1.apply(20);
        System.out.println(userFun1);

        //调用的是只有一个Integer类型和一个String类型的构造函数
        BiFunction<Integer,String,User> fun2 = User::new;
        User userFun2 = fun2.apply(10,"fangzy");
        System.out.println(userFun2);
    }
    /**
     * 数组引用
     * 用法Type[]::new
     */
    @Test
    public void lambdaTest15(){

        //创建一个指定长度的数组   正常lambda表达式写法
        Function<Integer,String[]> fun1 = (t) -> new String[t];
        String[] arr1 = fun1.apply(10);
        System.out.println("arr1数组的长度：" + arr1.length);

        //创建一个指定长度的数组   数组引用的写法
        Function<Integer,String[]> fun2 = String[]::new;
        String[] arr2 = fun2.apply(10);
        System.out.println("arr2数组的长度：" + arr2.length);
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

    @FunctionalInterface
    interface MyFunctionInterface{
        String getVal(String str);

    }
}
