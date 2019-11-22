package connector;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class SendMail {
	private static final String SMTP_SERVER = "ssl0.ovh.net";
    private static final String USERNAME = "osticket-alert@dieunelson.fr";
    private static final String PASSWORD = "osticket-alert";

    private static final String EMAIL_FROM = "osticket-alert@dieunelson.fr";
    private static final String EMAIL_TO = "osticket-alert@dieunelson.fr";
    private static final String EMAIL_TO_CC = "";
    
    private Properties prop;

    
    public SendMail() {
    	prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        System.out.println("OK");
        
	}
    
    public void send(String objet, String message){
    	
    	Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);
        
    	try {
    		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(objet);
			
			// content 
            msg.setText(message);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
