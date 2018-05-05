---
title: redis常用命令
---

###  String类型

> 存储的数据都是string类型的

-  set name fangzy  //设置值   name=fangzy     key为name    value为fangzy
-  get name    //获取key为name的值  
-  mset name fangzy age 20  //设置多个值   name=fangzy   age=20 
-  mget name age   //get获取name和age的值
-  getset name fangzy0723   //先获取name的值，再给name设置新值fangzy0723
-  del name   //删除key为name的值
-  incr age   //age的值每次自增1        （ incr：值为数则自增    字符串报错    值不存在先付0再自增）
-  incrby age 2     //age的值每次自增2
-  decr age   //age值每次自减1
-  decrby age 2  //age的值每次自减2
-  append name 你好   //name的值后面追加你好
-  strlen name  //获取key为name的值的长度，不存在返回0
-  select 1  //选择使用下标为1的数据库   默认使用的是下标为0的    redis默认是16个数据库下标从0-15
<!-- more -->
###  Hash类型（散列类型）

> 存储的数据是map（也就是value是map类型），map中字段的值必须是string类型

- hset  user  name  fangzy    //设置值    key：user    value是个数组   字段名：name   字段值：fangzy        
- hget user name    //获取key为user  字段名为name的值   
- hmset  user name fangzy age 20 // 给user设置多个值
- hmget user name age  //获取多个值
- hgetall user  //获取key为user下的所有字段名、字段值
- hdel user age //删除key为user的age的值
- hincrby user age 2  //key为user的age的字段值每次自增2
- hexists user age  //判断user中是否含有age字段    1：有  0：没有
- hkeys user //获取user中所有的字段名
- hvals user  //获取user中所有的字段值
- hlen user  //获取user中的字段的长度

###  List类型（列表类型） 

>   ArrayList：使用数组存储数据，查询快，增删慢。LinkedList：使用双向链表存储数据，查询慢，增删快。redis采用LinkedList双向链表存储数据，向两头插值

- lpush list_1 1 2 3 4 //从左边向list_1插值   插入之后的值的顺序是  4 3 2 1
- rpush list1 1 2 3 4  //从右边向list_1插值  插入之后值的顺序是1 2 3 4 
 ```
 示例：lpush   asd   1 2 3 4；
            rpush  asd   a b c ;
上面命令之后asd列表中的元素为4 3 2 1 a b c
```
- lrange asd 0 2   //获取列表asd中下标从0到2的片段    列表的下边是从0 开始代表第一个   -1代表最后一个
- lpop asd   //从左边弹出asd列表中的第一个元素，相当于删除左边第一个元素
- rpop asd  //从右边弹出asd列表中的第一个元素，相当于删除右边第一个元素
-  llen asd  //获取列表asd的元素个数
- lrem asd count a   //删除asd列表中的a元素，count>0   从左边删除第一个a    count<0  从右边删除第一个a   count=0   删除所有的a元素
- lindex asd 0  //获取指定下标的元素值     获取asd下标为0的元素
- lset asd 0 a //设置指定下标的值      给asd列表中下标为0的元素重新赋值为a

###  set（集合）类型

> 无序不可重复

- add set1 1 2 3 4 //给set1集合设置元素
```
示例：sadd set1  1 2 3 4 2 3 1   //集合中有1 2 3 4 四个元素（顺序不确定）
```
- srem set1 1 2  //删除set1集合中值为1和2的元素
- smembers set1   //获取set1集合中所有的元素
- sismember  set1 1 //集合set1中是否存在1元素    返回值是1：存在   0：不存在
- scard set1 //获取set1集合中的元素个数
- spop set1  //从集合中弹出一个元素，因为集合石无须的，所以是随机弹出一个元素
```
示例： sadd k1  1 2 3；
             sadd k2  2 3 4 ；
sdiff k1 k2 //获取k1中有k2中没有的元素（“1”）  求差集
sinter k1 k2 //获取k1和k2相同的元素 （“2”，“3”） 求交集
sunion k1 k2   //获取k1、k2包含的所有的元素 （“1”，“2”，“3”，“4”）  求并集
```

###  zset 有序集合  
> 跟set集合的区别，每个元素多一个score   根据这个分数排序，分数值越大排名越靠前

- zadd k score1 v1 score2 v2     //向有序集合添加元素   score是分数，排名用的，数值越大该元素排名越靠前
```
示例：zadd zset1 10 a  9 b 11 c     
插入之后zset的集合顺序是：c a b
```
- zscore zset1 a   //获取zset1集合a元素的分数
- zrem zset1 a     //删除zset1集合中元素
- zrange zset1 0 2    //获取zset1集合下标从0-2的元素   （按分数从小到大）
- zrevrange zset1  0 2  //获取zset1集合下标从0-2的元素  （按分数从大到小）
- zrange zset1  0 2 withscores    //获取zset1集合下标从0-2的元素并显示分数
- zrank zset1 a   //获取a在zset1集合中的排名（从小到大的排名）
- zrevrank zset1 a   //获取a在zset1集合中的排名（从大到小的排名）
```
示例：
zadd goods 9 1002 10 1003  //插入商品编号1002  销量为9  ，1003销量为10
zincrby goods 1 1002            //商品1002的销量加1
zrverange goods 0 9 withscores   //获取销量前十的商品
```
