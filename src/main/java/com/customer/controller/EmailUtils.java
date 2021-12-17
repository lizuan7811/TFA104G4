package com.customer.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import com.customer.model.CustomerVO;

//需import activation-1.1.1.jar以及javax.mail-1.6.2.jar
public class EmailUtils {
	
	private final static String HOST = "smtp.gmail.com";
	private final static String AUTH = "true";
	private final static String PORT = "587";
	private final static String STARTTLE_ENABLE = "true";
	private final static String SENDER = "t8metfa104gp4@gmail.com";
	private final static String PASSWORD = "gbtjsyhpwaypfbfa";
		
	public static void sendAccountActivateEmail(CustomerVO custVO, String link) {
		String mailSubject = "【食健】啟用帳戶";
		String mailBody = custVO.getName() + "您好，請點擊以下連結啟用食健帳戶：" +
				"<a href='" + link + "'>啟用帳戶</a>";
		sendEmail(custVO.getEmail(), mailSubject, mailBody);
	}
	
	public static void sendPasswordResetEmail(CustomerVO custVO, String link) {
		String mailSubject = "【食健】重設密碼";
		String mailBody = custVO.getName() + "您好，請點擊以下連結重設食健密碼：" +
				"<a href='" + link + "'>重設密碼</a>";
		sendEmail(custVO.getEmail(), mailSubject, mailBody);
	}
	
	public static void sendEmail(String recipients, String mailSubject, String mailBody) {
			
		String recipientCcs = "t8metfa104gp4@gmail.com";
			
		//設定登入資訊
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", STARTTLE_ENABLE);
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER, PASSWORD);
			}
		};

		Session session = Session.getDefaultInstance(props, authenticator);
		Message message = new MimeMessage(session);
		try {
//			設定Email Message start
				
//			設定寄件人、收件人、副本、主旨
			message.setSentDate(new Date());
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(recipientCcs));
			message.setSubject(MimeUtility.encodeText(mailSubject, StandardCharsets.UTF_8.toString(), "B"));

//			設定內容
			MimeBodyPart messageBody = new MimeBodyPart();
			messageBody.setContent(mailBody, "text/html; charset=UTF-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBody);

			message.setContent(multipart);
//			設定Email Message end			

//			寄出email
			Transport transport = session.getTransport("smtp");
			try {
				transport.connect();
				transport.sendMessage(message, message.getAllRecipients());
			} finally {
				transport.close();
			}
				
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}


 	

