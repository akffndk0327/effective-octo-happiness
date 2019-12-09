package kr.or.ddit.member.controller;

import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class FindIdController {

	@Inject
	IMemberService service;
	
	AccountVO account = new AccountVO();
	
	@RequestMapping("findId.do")
	public String findId(@ModelAttribute("member") MemberVO member, Model model) {

		String viewName = "login/loginForm";
		MemberVO findMember = service.findId(member);
		if (findMember == null) {
			model.addAttribute("noMem", member);
		} else {
			model.addAttribute("findId", findMember);
		}
		return viewName;
	}

	@RequestMapping("findPass.do")
	public String findPass(@ModelAttribute("member") MemberVO member, Model model) throws Exception {

		String viewName = "login/loginForm";
		MemberVO findPassMember = service.findPass(member);
		
		account.setMemId(member.getMemId());
		//임시비밀번호를 넣어주고
		account.setMemPass(getNewPW());
		if (findPassMember == null) {
			model.addAttribute("noPass", member);
		} else {
			//member에 있는 메일주소로 변경된 임시 비밀번호를 보내줘야함.
			sendEmail(member);
			//임시비밀번호로 비밀번호 수정
			ServiceResult result = service.updatePass(account);			
			model.addAttribute("findPass", findPassMember);
		}

		return viewName;
	}

	public void sendEmail(MemberVO member) throws Exception {

		String host = "smtp.naver.com"; // 네이버일경우

		final String username = "pojyd052"; // 네이버아이디
		final String password = "wlsl2012030083"; // 네이버비밀번호

		Properties props = System.getProperties(); // 정보를 달기위한 객체 생성

		// SMTP서버정보설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		// Session 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			 message.addRecipient(Message.RecipientType.TO, new
			 InternetAddress(member.getMemMail()));

			// Subject 제목
			message.setSubject("안녕하세요. AI(Allergy Information)임시 비밀번호 서비스 입니다. ");

			// Text 내용
			 message.setText(
					 member.getMemId() + "님의 임시 비밀번호는 " + account.getMemPass() + " 입니다."
					 );

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public String getNewPW() throws Exception {
	      char[] charSet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
	            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
	            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
	            'y', 'z' };

	      StringBuffer newKey = new StringBuffer();

	      for (int i = 0; i < 6; i++) {
	         int idx = (int) (charSet.length * Math.random());
	         newKey.append(charSet[idx]);
	      }
	      return newKey.toString();
	   }

	

}