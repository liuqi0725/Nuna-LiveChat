package com.liuqi.nuna.module.mail;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.mail.internet.AddressException;
import java.util.Arrays;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午2:28 2018/1/5
 */
public class MailEntry {
    /**
     * 收件人
     */
    private String[] recipients;
    /**
     * 抄送人
     */
    private String[] carbonCopy;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String text;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getRecipients() {
        return recipients.clone();
    }

    public void setRecipients(String[] recipients) throws AddressException {
        Assert.isTrue(ArrayUtils.isNotEmpty(recipients),"收件人不能为空");
        String[] addresses = new String[recipients.length];
        for (int i = 0; i < addresses.length; i++) {
            addresses[i] = parseAddress(recipients[i]);
        }
        this.recipients = addresses;
    }

    public String[] getCarbonCopy() {
        if(ArrayUtils.isNotEmpty(carbonCopy)){
            return carbonCopy.clone();
        }
        return ArrayUtils.EMPTY_STRING_ARRAY;
    }

    public void setCarbonCopy(String[] carbonCopy) throws AddressException {
        String[] addresses = new String[carbonCopy.length];
        for (int i = 0; i < addresses.length; i++) {
            addresses[i] = parseAddress(carbonCopy[i]);
        }
        this.carbonCopy = addresses;
    }

    /**
     * @desc 只要设定邮件接受人的域账号即可自动增加企业后缀
     * @author jianzh5
     * @date 2017/4/1 13:43
     * @param recipient 收件人域账号
     * @return 带企业后缀的邮箱地址
     * @throws AddressException
     */
    private String parseAddress(String recipient) throws AddressException {
        if(StringUtils.isEmpty(recipient)){
            throw new AddressException("邮箱账号不能为空",recipient);
        }
        return recipient + "@company.com";
    }

    @Override
    public String toString() {
        return "MailEntry{" +
                "recipients=" + Arrays.toString(recipients) +
                ", carbonCopy=" + Arrays.toString(carbonCopy) +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
