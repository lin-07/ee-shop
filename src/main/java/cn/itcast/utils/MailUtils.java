package cn.itcast.utils;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * @author lin-PC
 */
public class MailUtils {

	public static void send(String sendMail,String msg) {
		try {
			//1 获得session
			Properties props = new Properties();
			props.setProperty("mail.host", "smtp.126.com");
			props.setProperty("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("linqing_07@126.com", "AAWKHDOZARRCFNSS");
				}
			});
			//2 message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("linqing_07@126.com"));
			message.setRecipient(RecipientType.TO, new InternetAddress(sendMail));
			message.setSubject("用户激活");
			message.setContent(msg, "text/html;charset=UTF-8");
			
			//3发送
			Transport.send(message);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	public static void main(String[] args) {
		send("jack@itheima.com", "嫐 嬲  ");
	}
}
