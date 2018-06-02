- #### pom添加依赖

```
    <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

- ### 配置文件配置

```
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/my_db?useUnicode = true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
spring.datasource.username=root
spring.datasource.password=123456


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

- #### domain

```
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
    //get、set、无参构造函数、toString方法
}
```

- #### Repository

```
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
```

- #### service

```
public interface CustomerService {

    /**
     * 调用spring data jpa提供的方法查询全部数据
     * @return
     */
    List<Customer> findAll();

    /**
     * 调用spring data jpa提供的方法查根据主键拆查询
     * @param id
     * @return
     */
    Customer findById(Integer id);
    /**
     * 调用spring data jpa提供的方法进行插入
     */
    Customer save();

    /**
     * 调用spring data jpa提供的方法进行修改
     */
    Customer update(Integer id);

    /**
     * 调用spring data jpa提供的方法根据id进行删除
     * @param id
     */
    void deleteById(Integer id);


    /**
     * 根据spring data jpa 命名规则定义查询方法
     * @param name
     * @return
     */
    List<Customer> findByName(String name);

    /**
     * 使用@Query注解 执行SQL查询语句
     * @param name
     * @return
     */
    List<Customer> findByNameByQuerySQL(String name);

    /**
     * 使用@Query注解 执行JPQL查询语句
     * @param name
     * @return
     */
    List<Customer> findByNameByQueryJPQL(String name);

    /**
     * 调用spring data jpa提供的根据字段排序
     * @param id 排序字段
     * @return
     */
    List<Customer> findAllByIdSort(String id);

    /**
     * 调用spring data jpa提供的分页查询
     * @param page  第几页（从0开始  0：第一页）
     * @param pageSize  每页的条数
     * @return
     */
    List<Customer> findAllPage(Integer page,Integer pageSize);

    /**
     * 调用spring data jpa提供的分页并排序查询
     * @param id 排序字段
     * @param page  第几页（从0开始  0：第一页）
     * @param pageSize  每页的条数
     * @return
     */
    List<Customer> findAllPageAndSort(String id,Integer page,Integer pageSize);

    /**
     * 根据条件动态拼查询条件
     * @param id 查询条件
     * @param name 查询条件
     * @return
     */
    List<Customer> findByConditions1(Integer id,String name);

    /**
     * 根据条件动态拼查询条件
     * @param id 查询条件
     * @param name 查询条件
     * @return
     */
    List<Customer> findByConditions2(Integer id,String name);
}
```

- #### service实现类

```
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
}
```

- #### 测试类

```
@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultCustomerServiceTest {
    @Autowired
    private CustomerService customerService;


    @Test
    public void findAll() throws Exception {
        customerService.findAll();
    }
    @Test
    public void findById() {
        customerService.findById(1);
    }

    @Test
    public void save() {
        customerService.save();
    }

    @Test
    public void update() {
        customerService.update(6);
    }

    @Test
    public void deleteById() {
        customerService.deleteById(6);
    }

    @Test
    public void findByName() {
        customerService.findByName("zhangsan");
    }

    @Test
    public void findByNameByQuerySQL() {
        customerService.findByNameByQuerySQL("zhangsan");
    }

    @Test
    public void findByNameByQueryJPQL() {
        customerService.findByNameByQueryJPQL("zhangsan");
    }

    @Test
    public void findAllByIdSort() {
        customerService.findAllByIdSort("id");
    }

    @Test
    public void findAllPage() {
        customerService.findAllPage(1,2);
    }

    @Test
    public void findAllPageAndSort() {
        customerService.findAllPageAndSort("id",0,5);

    }

    @Test
    public void findByConditions1() {
        customerService.findByConditions1(3,"zhangsan");
    }

    @Test
    public void findByConditions2() {
        customerService.findByConditions2(3,"zhangsan");
    }

}
```
