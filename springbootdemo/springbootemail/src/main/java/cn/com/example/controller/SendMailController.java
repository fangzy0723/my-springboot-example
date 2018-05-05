package cn.com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.UUID;

/**
 * 测试spring boot 整合javaMail发送邮件
 * Created by fangzy on 2018/2/12 9:09
 */
@RestController
public class SendMailController {
    Logger logger = LoggerFactory.getLogger(SendMailController.class);
    @Autowired
    private JavaMailSenderImpl mailSender;
    /**
     * 发送纯文本内容
     */
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

    /**
     * 发送html内容
     * @throws Exception
     */
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
        logger.info("邮件已发送");
    }
    /**
     * 发送包含内嵌图片的邮件
     * @throws Exception
     */
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
        logger.info("邮件已发送");
    }

    /**
     * 发送包含附件的邮件
     * @throws Exception
     */
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
        logger.info("邮件已发送");
    }
}
