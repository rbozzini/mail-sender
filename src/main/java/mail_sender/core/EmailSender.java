package mail_sender.core;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mail_sender.ApplicationProperties;
import mail_sender.model.Email;

public class EmailSender {
	
	public void send(Email email, ApplicationProperties properties) throws Exception {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", properties.getAuth());
		props.put("mail.smtp.starttls.enable", properties.getStarttlsEnable());
		props.put("mail.smtp.host", properties.getHost());
		props.put("mail.smtp.port", properties.getPort());

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getUsername(), properties.getPassword());
			}
		  });

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email.getFrom()));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(email.getTo()));
		message.setSubject(email.getSubject());
		message.setText(email.getMessage());

		Transport.send(message);
	}

}
