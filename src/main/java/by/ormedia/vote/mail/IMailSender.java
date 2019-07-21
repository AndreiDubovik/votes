package by.ormedia.vote.mail;

import by.ormedia.vote.entity.Subject;

public interface IMailSender {
	
	public void sendLinks(Subject subject);
	public void respondInitiator(Subject subject);

}
