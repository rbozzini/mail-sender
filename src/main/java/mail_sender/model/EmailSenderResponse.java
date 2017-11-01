package mail_sender.model;

public class EmailSenderResponse {
	
	public static String status_ok = "ok";
	public static String status_error = "error";
	
	private String from;
	private String to;
	private String subject;
	private String message;
	private String status;
	private String responseMessage;
	
	public EmailSenderResponse (String from, String to, String subject, String message, String status, String responseMessage) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.status = status;
		this.responseMessage = responseMessage;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	
}
