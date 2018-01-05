package com.liuqi.nuna.livechat.service.impl;

import com.liuqi.nuna.livechat.other.sys.SystemParams;
import com.liuqi.nuna.livechat.other.tool.CommonTools;
import com.liuqi.nuna.livechat.pojo.ChatUser;
import com.liuqi.nuna.livechat.service.ChatMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午2:52 2018/1/5
 */
@Service
public class ChatMailServiceImpl implements ChatMailService{

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmMail(ChatUser user) {

        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mime, true, "utf-8");
            helper.setTo(user.getEmail());// 收件人邮箱地址
            helper.setFrom(SystemParams.CHAT_MAIL_SETTING.getString("username"));// 发件人
            helper.setSubject(SystemParams.CHAT_MAIL_SETTING.getString("subject_prefix") + " 注册确认");// 主题

            StringBuilder sb = new StringBuilder();
            sb.append("<html><head><title>NUNA-LiveChat 注册确认</title></head><body>");
            sb.append("<h1>NUNA-LiveChat - 注册确认</h1>");
            sb.append("<hr/>");
            sb.append("<h3>亲爱的 : ").append(user.getName()).append("</h3>");
            sb.append("<p>感谢注册 NUNA-LiveChat !</p>");
            sb.append("<p>现在,您只需要点击以下链接,即可激活您的账号。<span style='color:red;'>(链接 1 小时内有效)</span></p>");

            String url = SystemParams.APP_WEB_PATH + "/confirmed?token=" + CommonTools.generate_user_token(3600,"id",user.getId());


            sb.append("<a target=\"_blank\" id=\"url\" href=\"").append(url).append("\">").append(url).append("</a><br/>");

            sb.append("<p>").append(new Date()).append("</p>");
            sb.append("<div style='font-size: 12px;color: #BBBBBB'>");
            sb.append("<p> 如果以上链接无法访问，请将该网址复制并粘贴至新的浏览器窗口中</p>");
            sb.append("<p>如果你错误地收到了此电子邮件，你可以放心忽略此封邮件，无需进行任何操作</p>");
            sb.append("<p>此邮件为系统邮件,请勿回复。</p>");
            sb.append("</div>");

            sb.append("</body></html>");
            helper.setText(sb.toString(),true);// 正文  true 可以发送 html
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        mailSender.send(mime);
    }

}
