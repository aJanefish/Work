package com.example.zy.emaildemo.utils;

import android.content.Context;
import android.icu.text.AlphabeticIndex;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService {
	
	public static void send_email(Context context, String content) throws IOException, MessagingException {
		
		Properties properties = new Properties();
		String to = "收信地址";
		String subject = "提交日志";
		
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
		
		mailMessage.setContent(addFile(context));
		
		long t0, t1;
		t0 = System.currentTimeMillis();
		Log.i("email","Running send started.");
		Transport.send(mailMessage);
		t1 = System.currentTimeMillis();
		Log.i("email","Running send finished. Cost " + (t1 - t0) + " ms.");
	}
	
	public static  Multipart addFile(Context context) {
		// create the Multipart and its parts to it
		Multipart mp = new MimeMultipart();
		
		// create and fill the first message part
		MimeBodyPart mbp1 = new MimeBodyPart();
		try {
			mbp1.setText(""); // create and fill the second message part
			mp.addBodyPart(mbp1);
			
			File file = getDiskCacheDir(context, "my.xls");
			
			if (!file.exists()) {
				try {
					boolean falg = file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			String fileName = file.getName();
			String filePath = file.getAbsolutePath();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			mp.addBodyPart(messageBodyPart);
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return mp;
	}
	
	private static File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}
}

