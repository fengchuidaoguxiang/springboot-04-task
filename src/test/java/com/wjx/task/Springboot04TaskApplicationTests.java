package com.wjx.task;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {

    @Autowired
    JavaMailSender mailSender;

    /**
     * 简单邮件
     */
    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();

        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("dsadsds今晚7:30开会");

        message.setTo("15049420781@163.com");
        message.setFrom("2672829611@qq.com");
        mailSender.send(message);
    }

    /**
     * 复杂邮件（带附件）
     */
    @Test
    public void test2() throws MessagingException {
        //1.创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今晚7:30开会</b>",true);

        helper.setTo("15049420781@163.com");
        helper.setFrom("2672829611@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("E:\\spring文档\\springboot课件\\收藏\\3.jpg"));
        helper.addAttachment("2.jpg",new File("E:\\spring文档\\springboot课件\\收藏\\4.jpg"));

        mailSender.send(mimeMessage);
    }

}
