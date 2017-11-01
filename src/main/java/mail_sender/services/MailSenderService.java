package mail_sender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mail_sender.ApplicationProperties;
import mail_sender.core.EmailSender;
import mail_sender.model.Email;
import mail_sender.model.EmailSenderResponse;

@RestController
public class MailSenderService {
	
	// http://localhost:8080/email-sender?from=rossellabozzini@gmail.com&to=rossellabozzini@gmail.com&subject=test&message=Mail di test!

    @Autowired
    private ApplicationProperties properties;
    
    @RequestMapping("/email-sender")
    public @ResponseBody EmailSenderResponse sayHello(@RequestParam(value="from", required=true) String from,
    		@RequestParam(value="to", required=true) String to,
    		@RequestParam(value="subject", required=false, defaultValue="You've got mail!") String subject,
    		@RequestParam(value="message", required=false, defaultValue="This is a message for you!") String message) {
        
    	EmailSender sender = new EmailSender();
    	EmailSenderResponse response = null;
    	
    	try {
			sender.send(new Email(from, to, subject, message), properties);
			response = new EmailSenderResponse(from, to, subject, message, EmailSenderResponse.status_ok, "Mail inviata correttamente");
		} catch (Exception e) {
			response = new EmailSenderResponse(from, to, subject, message, EmailSenderResponse.status_error, "Problemi nell'invio dell'email: " + e.getMessage());
		}
    	
    	return response;
    }

}
