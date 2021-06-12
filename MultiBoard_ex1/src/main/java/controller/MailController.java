package controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import model.Mail;




@Controller
public class MailController {
	
	
	
	@RequestMapping("/mail/mailForm")
	public ModelAndView mailForm() {
		ModelAndView mav = new ModelAndView("mail/mail");
		return mav;
	}
	@RequestMapping("/mail/mail")
	public ModelAndView mail(Mail mail,HttpSession session) {
		ModelAndView mav = new ModelAndView("mail/mailSuccess");
		mailSend(mail);
		return mav;
	}
	//자바메일을 이용하여 메일 전송시 메일 서버에 인증 받기위한 객체
	private final class MyAuthenticator extends Authenticator {
		private String id;
		private String pw;
		public MyAuthenticator(String id, String pw) {
			this.id = id;
			this.pw = pw;
		}
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id,pw);
		}
	}
	//mail : 화면에서 입력한 정보 저장
	private void mailSend(Mail mail) {
		//메일 전송을 위한 환경 변수 설정
		MyAuthenticator auth = new MyAuthenticator(mail.getNaverid(),mail.getNaverpw());
		Properties prop = new Properties(); //Map 객체 
		prop.put("mail.smtp.host", "smtp.naver.com"); //메일 전송서버 주소 정보  
		prop.put("mail.smtp.starttls.enable", "true");//보안 서버
		prop.put("mail.user",mail.getNaverid() );
		prop.put("mail.from",mail.getNaverid()+"@naver.com" );
		prop.put("mail.debug","false"); //debug 상태로 실습하기
		prop.put("mail.smtp.auth","true"); //메일 전송시 인증 필수. 
		prop.put("mail.smtp.port","465");
		prop.put("mail.smtp.user",mail.getNaverid() ); //보내는 사람 설정
		prop.put("mail.smtp.socketFactory.port","465");
		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback","false");
		Session session = Session.getInstance(prop,auth); //메일 서버에 연결. 준비 완료
		//msg : session을 통해 전송되는 객체
		MimeMessage msg = new MimeMessage(session);  //메일로 전송할 객체 생성
		try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getNaverid()+"@naver.com"));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, 
            		new InternetAddress(mail.getRecipient())); 

            // Subject
            message.setSubject(mail.getTitle()); //메일 제목을 입력

            // Text
            message.setText(mail.getContents());    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



	}
	
}