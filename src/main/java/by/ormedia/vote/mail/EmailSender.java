package by.ormedia.vote.mail;

import java.util.Properties;
import java.util.Set;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import by.ormedia.vote.controller.Controller;
import by.ormedia.vote.entity.Item;
import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.entity.VoteTicket;

@Component
public class EmailSender implements IMailSender{
	
	private static String EMAIL_USERNAME = "andreidubovik2019";
	private static String EMAIL_PASSWORD = "ORMEDIAJAVA2019";
	private static String VOTE_IS_CREATED = "Вами было создано голосование";
	private static String TAKE_PART_IN_VOTE = "Пожалуйста проголосуйте";
	
	private Properties props;
    {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", EMAIL_USERNAME);
        props.put("mail.smtp.password", EMAIL_PASSWORD);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "imap");
    }

	@Override
	public void sendLinks(Subject subject) {
		Set<VoteTicket>tickets = subject.getVoteTickets();
		for(VoteTicket vt:tickets){
			StringBuilder sb = new StringBuilder();
			sb.append("Здравствуйте!");
			nextLine(sb);
			sb.append("Вас приглашают принять участие в голосовании");
			nextLine(sb);
			sb.append("Тема:");
			nextLine(sb);
			sb.append(subject.getSubject());
			nextLine(sb);
			Set<Item>items = subject.getItems();
			for(Item it:items){
				sb.append("...для голосования за пункт: ");
				nextLine(sb);
				sb.append("   - "+it.getText());
				nextLine(sb);
				sb.append("перейдите по ссылке: ");
				sb.append(getVoteString(vt,it));
				nextLine(sb);
			}
			send(TAKE_PART_IN_VOTE,sb.toString(),vt.getUser().getEmail());
		}
		
	}

	@Override
	public void respondInitiator(Subject subject) {
		StringBuilder sb = new StringBuilder();
		sb.append("Вами было создано голосование:");
		nextLine(sb);
		sb.append(subject.getSubject());
		nextLine(sb);
		sb.append("Просмотреть состояние можно по этой ссылке:");
		nextLine(sb);
		sb.append(this.getVoteInfoLink(subject));
		nextLine(sb);
		sb.append("Закрыть голосование можно по этой ссылке:");
		nextLine(sb);
		sb.append(this.getCloseVoteLink(subject));
		send(VOTE_IS_CREATED,sb.toString(),subject.getInitiator().getEmail());
	}
	
	private void nextLine(StringBuilder sb){
		sb.append((char)10);
		sb.append((char)13);
	}
	
	private void send(String messageSubject, String text, String toEmail){
    	Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        }); 
        
        try { 
             MimeMessage message = new MimeMessage(session); // email message 
             message.setFrom(new InternetAddress(EMAIL_USERNAME)); // setting header fields  
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); 
             message.setSubject(messageSubject); 
             message.setText(text); 
             Transport.send(message);
            } catch (MessagingException mex){ mex.printStackTrace(); } 

  } 
	
	private String getVoteString(VoteTicket vt, Item it){
		return "http://"+Controller.URL+Controller.VOTE_LINK+"?"+Controller.ITEM_ID+"="+it.getId()
		+"&"+Controller.VOTE_TICKET_ID+"="+vt.getId()+"&"+Controller.KEY_CODE+"="+vt.getKeycode();
	}
	
	private String getVoteInfoLink(Subject subject){
		return "http://"+Controller.URL+
				Controller.GET_SUBJECT_INFO+"?id="+subject.getId();
	}

	private String getCloseVoteLink(Subject subject){
		return "http://"+Controller.URL+Controller.CLOSE_VOTE
				+"?subjectid="+subject.getId()
				+"&userid="+subject.getInitiator().getId();
	}
}
