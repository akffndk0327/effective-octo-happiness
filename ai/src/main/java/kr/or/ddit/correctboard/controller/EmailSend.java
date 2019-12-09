package kr.or.ddit.correctboard.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sendMail.do")
public class EmailSend {

	@RequestMapping
	public void sendEmail() {
		
		String host = "smtp.naver.com"; //네이버일경우
		 
		final String username="pojyd052"; //네이버아이디
		final String password="wlsl2012030083"; //네이버비밀번호
		
		//받는 사람 이메일 주소
		String to="";
		//메일제목
		//메일 내용
		
		Properties props = System.getProperties(); //정보를 달기위한 객체 생성
		
		//SMTP서버정보설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
		//Session 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject 제목
			message.setSubject("안녕하세요.AI(Allergy Information)입니다. ");

			// Text 내용
//			message.setText(msg);

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");
//			MyAlert.info("결제내역이 이메일로 전송되었습니다.");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
