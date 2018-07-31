package com.example.zy.emaildemo.utils;

import java.io.IOException;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	public static void send_email(String content) throws IOException, MessagingException{
		
		Properties properties = new Properties();
		String to = "收信地址";
		String subject ="提交日志";
		
		properties.put("mail.smtp.host", "smtp.qq.com");
		properties.put("mail.smtp.port", "465");
		properties.setProperty("mail.debug", "true");
		properties.setProperty("mail.smtp.auth", "true");
		
		// 协议名称设置为smtps，会使用SSL
		properties.setProperty("mail.transport.protocol", "smtps");
		properties.put("mail.smtp.ssl.enable", "true");
		
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.put("mail.smtp.socketFactory.port", properties.get("mail.smtp.port").toString());
		//MyAuthenticator authenticator = new MyAuthenticator("发送地址", "授权码");
		//授权码需要在邮箱设置是获取
		MyAuthenticator authenticator = new MyAuthenticator("641519166@qq.com", "wnwbqxplrftwbfjj");
		javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress("641519166@qq.com"));
		
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("yu.zhang@singou.mo"));
		mailMessage.setSubject(subject, "UTF-8");
		mailMessage.setSentDate(new Date());
		mailMessage.setText(content);
		Transport.send(mailMessage);
	}
}

