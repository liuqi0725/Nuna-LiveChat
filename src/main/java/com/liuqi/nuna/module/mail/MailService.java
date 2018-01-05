package com.liuqi.nuna.module.mail;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午2:30 2018/1/5
 */
public class MailService {

    private Logger logger = LoggerFactory.getLogger(MailService.class);

    @Resource
    private TaskExecutor taskExecutor;
    @Resource
    private JavaMailSender javaMailSender;


    public void sendMail(MailEntry mailEntry) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom("xxx@company.com");
            helper.setTo(mailEntry.getRecipients()); //收件人
            if(ArrayUtils.isNotEmpty(mailEntry.getCarbonCopy())){
                helper.setCc(mailEntry.getCarbonCopy()); //抄送人
            }
            helper.setSubject(mailEntry.getSubject());
            helper.setText(mailEntry.getText(),true);//设置为TRUE则可以使用Html标记
            addSendMailTask(message);
        } catch (MessagingException e) {
//            e.printStackTrace();
            logger.error("邮件转换异常,邮件详细信息为{}",e.getMessage());
        }


    }

    /**
     * @desc 使用多线程发送邮件
     * @author jianzh5
     * @date 2017/4/1 11:41
     * @param message MimeMessage邮件封装类
     */
    private void addSendMailTask(final MimeMessage message){
        try{
            taskExecutor.execute(new Runnable() {

                public void run() {
                    javaMailSender.send(message);
                }
            });
        }catch (Exception e){
            logger.error("邮件发送异常,邮件详细信息为{}",e.getMessage());
        }

    }
}
