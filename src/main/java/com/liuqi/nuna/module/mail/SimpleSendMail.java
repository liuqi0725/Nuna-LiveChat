package com.liuqi.nuna.module.mail;

import com.liuqi.nuna.livechat.other.sys.SystemParams;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Author : alexliu
 * @Description : 发送邮件
 * @Date : Create at 上午11:00 2018/1/5
 */
public class SimpleSendMail {
    //邮件通信会话
    Session session;

    //连接邮件发送的账号与密码
//    private String username = SystemParams.CHAT_MAIL_SETTING.getString("username");
//    private String passwd = SystemParams.CHAT_MAIL_SETTING.getString("password");
//    private String sendHost = SystemParams.CHAT_MAIL_SETTING.getString("smtp");
//    private String subject_prefix = SystemParams.CHAT_MAIL_SETTING.getString("subject_prefix");

    private String username = "liuqi_0725@163.com";
    private String passwd = "3BVVKpqkYf9";
    private String sendHost = "smtp.163.com";
    private String subject_prefix = "[NUNA-LiveChat]";

    /**
     * 邮件配置参数和连接接收邮件服务器
     * @throws MessagingException
     */
    private void init() throws MessagingException {
        Properties properties=new Properties();
        //设置发送和接收协议
        properties.put("mail.transport.protocal", "smtp");
        //设置ssl的端口
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.port", "993");
        properties.setProperty("mail.imap.socketFactory.port", "993");/*
        properties.put("mail.imap.port", "993");
        properties.put("mail.smtp.port", "465");*/
        //smtp认证
        properties.put("mail.smtp.auth", "true");
        //设置发送和接收处理类
        properties.put("mail.transport.class", "com.sun.mail.smtp.SMTPTransport");
        properties.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
        //设置发送邮件服务器
        properties.put("mail.smtp.host",sendHost);
        //获取邮件通信会话
        Authenticator auth = new MailAuthenticator();
        session=Session.getDefaultInstance(properties,auth);
        session.setDebug(true);
    }

    /**
     * 发送文本邮件
     * @param toAddr
     * @param tilte
     * @param text
     * @return
     * @throws MessagingException
     */
    public Message sendTextMail(String toAddr,String tilte,String text) throws MessagingException{
        //建立一封邮件
        MimeMessage message=new MimeMessage(session);
        //设置发送者和接收者
        message.setFrom(new InternetAddress(this.username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
        //设置主题
        message.setSubject(this.subject_prefix + " " +tilte);
        //设置日期
        message.setSentDate(new Date(System.currentTimeMillis()));
        //设置正文
        message.setText(text);
        return message;
    }

    /**
     * 发送 HTML 邮件
     * @param toAddr
     * @param tilte
     * @param html
     * @return
     * @throws MessagingException
     */
    public Message sendHtmlMail(String toAddr,String tilte,String html) throws MessagingException{
        //建立一封邮件
        MimeMessage message=new MimeMessage(session);
        //设置发送者和接收者
        message.setFrom(new InternetAddress(this.username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
        //设置主题
        message.setSubject(this.subject_prefix + " " +tilte);
        //设置日期
        message.setSentDate(new Date(System.currentTimeMillis()));
        //设置正文
        Multipart mp=createMultiPart();
        BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
        mdp.setContent(html,"text/html;charset=UTF-8");//给BodyPart对象设置内容和格式/编码方式
        Multipart mm=new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对象(事实上可以存放多个)
        mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)

        message.setContent(mp);

        return message;
    }


    /**
     * 创建复杂的正文
     * @return
     * @throws MessagingException
     */
    private Multipart createMultiPart() throws MessagingException {
        Multipart multipart=new MimeMultipart();

        //第一块
        BodyPart bodyPart1=new MimeBodyPart();
        bodyPart1.setText("创建复杂的邮件，此为正文部分");
        multipart.addBodyPart(bodyPart1);

        //第二块 以附件形式存在
        MimeBodyPart bodyPart2=new MimeBodyPart();
        //设置附件的处理器
        FileDataSource attachFile=new FileDataSource(ClassLoader.getSystemResource("attach.txt").getFile());
        DataHandler dh=new DataHandler(attachFile);
        bodyPart2.setDataHandler(dh);
        bodyPart2.setDisposition(Part.ATTACHMENT);
        bodyPart2.setFileName("test");
        multipart.addBodyPart(bodyPart2);

        return multipart;
    }

    /**
     * 发送邮件
     * @param message
     * @throws MessagingException
     */
    public void send(Message message) throws MessagingException{
        Transport.send(message);
    }

    private static Document confirmHtmlDocument = null;

    public static Document getDocument(String filePath){

        if(confirmHtmlDocument == null){
            //获取模板html文档
            SAXReader reader = new SAXReader();
            try {
                confirmHtmlDocument = reader.read(filePath);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        return confirmHtmlDocument;

    }

    public static void read(int index){
//        SAXReader reader = new SAXReader();
        Document document = null;
        try {

//            System.out.println(SimpleSendMail.class.getResource(""));
//
//            String path = SimpleSendMail.class.getResource("") + "unconfirmed.html";

            long start_time = System.nanoTime();

            File file = new File("/Users/alexliu/DEV/InteJ-JAVA/NunaLiveChat/src/main/resources/config/email/confirm.html");

            String dirPath = file.getParentFile().getAbsolutePath();

            String path = file.getAbsolutePath();
//            File file = new File("unconfirmed.html");
//
//            String path = file.getAbsolutePath();//SimpleSendMail.class.getResource("/unconfirmed.html").getPath();

            //获取模板html文档
            document = getDocument(path);
            //document = reader.read(path);
            Element root = document.getRootElement();
            //分别获取id为name、message、time的节点。
            Element name = getNodes(root,"id","username");
            Element email_title = getNodes(root,"id","email_title");


            Element url = getNodes(root,"id","url");

            Element time = getNodes(root, "id", "time");

            //设置收件人姓名，通知信息、当前时间
            Calendar calendar = Calendar.getInstance();
            time.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
            name.setText("Alexliu");

            email_title.setText("注册确认");

//            Attribute a = new Attribute();
//            a.setName("href");
//            a.setValue("http://wwwwww.dsaf.as.fas.f.asf");

            url.setText("http://www.dsaf.as.fas.f.asf");

            List<Attribute> list = url.attributes();

            for(Attribute attribute : list){
                if(attribute.getName().equals("href")){
                    attribute.setValue("http://www.dsaf.as.fas.f.asf");
                    break;
                }
            }
            url.setAttributes(list);

            System.out.println(document.toString());

            //保存到临时文件
            FileWriter fwriter = new FileWriter(dirPath + "/temp-"+index+".html");
            XMLWriter writer = new XMLWriter(fwriter);
            writer.write(document);
            writer.flush();

            long end_time = System.nanoTime();

            System.out.println(index + " >> " + (end_time-start_time) + " ns");


//            //读取临时文件，并把html数据写入到字符串str中，通过邮箱工具发送
//            FileReader in = new FileReader("d:/temp.html");
//            char[] buff = new char[1024*10];
//            in.read(buff);
//            String str = new String(buff);
//            System.out.println(str.toString());
//
//            new MailSender.Builder(str.toString(),"xxx@qq.com").send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法描述：递归遍历子节点，根据属性名和属性值，找到对应属性名和属性值的那个子孙节点。
     * @param node 要进行子节点遍历的节点
     * @param attrName 属性名
     * @param attrValue 属性值
     * @return 返回对应的节点或null
     */
    public static Element getNodes(Element node, String attrName, String attrValue) {

        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
            final String name = attr.getName();// 属性名称
            final String value = attr.getValue();// 属性的值
            System.out.println("属性名称：" + name + "---->属性值：" + value);
            if(attrName.equals(name) && attrValue.equals(value)){
                return node;
            }
        }
        // 递归遍历当前节点所有的子节点
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (Element e : listElement) {// 遍历所有一级子节点
            Element temp = getNodes(e,attrName,attrValue);
            // 递归
            if(temp != null){
                return temp;
            };
        }

        return null;
    }

    public static void main(String[] args){
        //SimpleSendMail sendReceiveMessage = new SimpleSendMail();
        try {
            for(int i=0; i<5; i++){
                SimpleSendMail.read(i);
            }
//            sendReceiveMessage.init();
//            Message message=sendReceiveMessage.sendTextMail("liuqi_0725@aliyun.com","test","test-content");
//            sendReceiveMessage.send(message);
//            message=sendReceiveMessage.createComplexMessage(sendReceiveMessage.username, "liuqi_0725@aliyun.com");
//            sendReceiveMessage.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆认证
     * @author u1
     *
     */
    private class MailAuthenticator extends Authenticator{

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            // TODO Auto-generated method stub
            return new PasswordAuthentication(username, passwd);
        }

    }

}
