package by.ormedia.vote.mail;

import org.springframework.stereotype.Component;

import by.ormedia.vote.entity.Subject;

@Component
public class EmailSender implements IMailSender{

	@Override
	public void sendLinks(Subject subject) {
		
		System.out.println(subject.getInitiator().getEmail());
		
	}

	@Override
	public void respondInitiator(Subject subject) {
		System.out.println("I am here");
		
	}

}
