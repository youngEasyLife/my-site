package cn.luischen.service;


import org.apache.commons.mail.SimpleEmail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * @ClassName : commEmail
 * @DesCription :TODO
 * @Author : young
 * @Date: 2018/11/30 17:32
 **/
public class commEmail {
    /**
     * 发送文本邮件
     *
     * @throws Exception
     */
    public static boolean sendTextMail(String strMail, String strTitle, String strText) throws Exception {
        boolean bret = false;
        SimpleEmail mail = new SimpleEmail();
        // 设置邮箱服务器信息
        mail.setSslSmtpPort("25");
        mail.setHostName("smtp.163.com");
        // 设置密码验证器
        mail.setAuthentication("youngeasylife@163.com", "365597937m");
        // 设置邮件发送者
        mail.setFrom("youngeasylife@163.com");
        // 设置邮件接收者
        mail.addTo(strMail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject(strTitle);
        // 设置邮件内容
        mail.setMsg(strText);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
        return bret;
    }


    public static void sendSSLEmail(String emailAddress, String content, String subject) {
        //emailAddress  收件人邮箱
        boolean isSSL = true;
        String host = "smtp.163.com";
        int port = 465;
        String from = "youngeasylife@163.com"; // 发件人的email
        boolean isAuth = true;
        String pass = "365597937m";

        Properties props = new Properties();
        props.put("mail.smtp.ssl.enable", isSSL);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", isAuth);

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            sendSSLEmail("365597937@qq.com", "测试邮箱发送", "你们好吗???");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

