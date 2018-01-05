import com.alibaba.fastjson.JSONObject;
import com.liuqi.nuna.livechat.pojo.ChatUser;
import com.liuqi.nuna.livechat.service.ChatMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 上午11:02 2018/1/5
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:/spring/application-context.xml","classpath*:/spring/application-ds.xml"})
@ContextConfiguration(locations = {"classpath*:/spring/application-context.xml","classpath*:/spring/application-ds.xml"})
public class MailTest {


    @Autowired
    ChatMailService service;

    @Test
    public void testMail(){
        ChatUser user = new ChatUser();
        user.setName("liuqi");
        user.setEmail("liuqi_0725@aliyun.com");
        service.sendConfirmMail(user);
    }


//
//    public static void main(String[] args) {
//
////        JSONObject mail_config = new JSONObject();
////
////        mail_config.put("send_name","Nuna-Admin");
////        mail_config.put("send_mail","liuqi_0725@163.com");
////        mail_config.put("subject_prefix","[NUNA-LiveChat]");
////        mail_config.put("smtp","smtp.163.com");
////        mail_config.put("port","25");
////        mail_config.put("username","liuqi_0725@163.com");
////        mail_config.put("password","3BVVKpqkYf9");
////
////        System.out.println(mail_config.toString());
//
//        ApplicationContext ac = new ClassPathXmlApplicationContext("/Users/alexliu/DEV/InteJ-JAVA/NunaLiveChat/src/main/resources/spring/application-context.xml");
//
//        ChatMailService sender = (ChatMailService) ac.getBean(ChatMailService.class);
//
//        sender.sendMail();
//
//        /*SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo("673376601@qq.com");// 收件人邮箱地址
//        mail.setFrom("zlg");// 收件人
//        mail.setSubject("SpringMailTest");// 主题
//        mail.setText("测试Spring自带邮件发送功能");// 正文
//        sender.send(mail);*/
////        JavaMailSender javaMailSender = sender.getMailSender();
////        MimeMessage mime = javaMailSender.createMimeMessage();
////        MimeMessageHelper helper;
////        try {
////            helper = new MimeMessageHelper(mime, true, "utf-8");
////            helper.setTo("dszlg@163.com");// 收件人邮箱地址
////            helper.setFrom("dszlg@163.com");// 收件人
////            helper.setSubject("SpringMailTest");// 主题
////            helper.setText("测试Spring自带邮件发送功能");// 正文
////        } catch (MessagingException me) {
////            me.printStackTrace();
////        }
////        javaMailSender.send(mime);
//    }

}
