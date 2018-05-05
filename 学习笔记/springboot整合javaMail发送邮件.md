

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9

- #### 发送邮件的类

> 新建SendMailController用于测试spring boot 整合javaMail发送邮件的类并注入JavaMailSenderImpl对象

```
@Autowired
private JavaMailSenderImpl mailSender;
```

1.发送纯文本内容示例

```
    @RequestMapping(value = "/sendTxtMail")
    public void sendTxtMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(new String[] {"731510438@qq.com"});
        simpleMailMessage.setFrom("fangzy0723@sina.com");
        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
        simpleMailMessage.setText("这里是一段简单文本。");
        // 发送邮件
        mailSender.send(simpleMailMessage);
        logger.info("邮件已发送");
    }
```

2.发送html内容

> 点击链接会请求到RegeinsterController类用于模仿邮箱验证

```
    @RequestMapping(value = "/sendHtmlMail")
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(new String[] {"fangzy0723@gmail.com"});
        mimeMessageHelper.setFrom("fangzy0723@sina.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>这是一封测试激活的邮件</h1><a href=\"http://localhost:8080/regeinsterController/"+ UUID.randomUUID().toString().replaceAll("-","")+"\">http://localhost:8080/regeinsterController/"+UUID.randomUUID().toString().replaceAll("-","")+"</a></body>");
        sb.append("</html>");
        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);
    }
```

3.发送包含内嵌图片的邮件

```
    @RequestMapping(value = "/sendAttachedImageMail")
    public void sendAttachedImageMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo("fangzy0723@gmail.com");
        mimeMessageHelper.setFrom("fangzy0723@sina.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【图片】");
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>");
        // cid为固定写法，imageId指定一个标识
        sb.append("<img src=\"cid:imageId\"/></body>");
        sb.append("</html>");
        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File("D:/11.jpg"));
        mimeMessageHelper.addInline("imageId", img);
        // 发送邮件
        mailSender.send(mimeMessage);
    }
```

4.发送包含附件的邮件

```
    @RequestMapping(value = "/sendAttendedFileMail")
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo(new String[] {"fangzy0723@gmail.com"});
        mimeMessageHelper.setFrom("fangzy0723@sina.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");
        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置附件
        FileSystemResource img = new FileSystemResource(new File("D:/11.jpg"));
        mimeMessageHelper.addAttachment("image.jpg", img);
        // 发送邮件
        mailSender.send(mimeMessage);
    }
```

- #### 模仿邮箱验证的类

> 新建RegeinsterController用于模仿用户注册时的邮箱验证

```
    /**
     * 获取请求参数
     * @param code
     */
    @GetMapping("/regeinsterController/{code}")
    public void regeinsterController(@PathVariable String code){
        System.out.println(code);
    }
```

- #### 配置信息

```
spring.mail.host= smtp.sina.com
#发送邮件的邮件地址
spring.mail.username= fangzy0723@sina.com
#发送邮件的登陆密码
spring.mail.password= 邮箱密码
spring.mail.port=25
spring.mail.protocol= smtp
```
- #### 添加依赖

> 在pom文件中添加如下依赖

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-mail</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```