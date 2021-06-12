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
	//�ڹٸ����� �̿��Ͽ� ���� ���۽� ���� ������ ���� �ޱ����� ��ü
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
	//mail : ȭ�鿡�� �Է��� ���� ����
	private void mailSend(Mail mail) {
		//���� ������ ���� ȯ�� ���� ����
		MyAuthenticator auth = new MyAuthenticator(mail.getNaverid(),mail.getNaverpw());
		Properties prop = new Properties(); //Map ��ü 
		prop.put("mail.smtp.host", "smtp.naver.com"); //���� ���ۼ��� �ּ� ����  
		prop.put("mail.smtp.starttls.enable", "true");//���� ����
		prop.put("mail.user",mail.getNaverid() );
		prop.put("mail.from",mail.getNaverid()+"@naver.com" );
		prop.put("mail.debug","false"); //debug ���·� �ǽ��ϱ�
		prop.put("mail.smtp.auth","true"); //���� ���۽� ���� �ʼ�. 
		prop.put("mail.smtp.port","465");
		prop.put("mail.smtp.user",mail.getNaverid() ); //������ ��� ����
		prop.put("mail.smtp.socketFactory.port","465");
		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback","false");
		Session session = Session.getInstance(prop,auth); //���� ������ ����. �غ� �Ϸ�
		//msg : session�� ���� ���۵Ǵ� ��ü
		MimeMessage msg = new MimeMessage(session);  //���Ϸ� ������ ��ü ����
		try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getNaverid()+"@naver.com"));

            //�����ڸ����ּ�
            message.addRecipient(Message.RecipientType.TO, 
            		new InternetAddress(mail.getRecipient())); 

            // Subject
            message.setSubject(mail.getTitle()); //���� ������ �Է�

            // Text
            message.setText(mail.getContents());    //���� ������ �Է�

            // send the message
            Transport.send(message); ////����
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