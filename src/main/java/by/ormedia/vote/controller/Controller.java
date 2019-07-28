package by.ormedia.vote.controller;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.ormedia.vote.entity.Item;
import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.entity.User;
import by.ormedia.vote.mail.IMailSender;
import by.ormedia.vote.service.IItemService;
import by.ormedia.vote.service.ISubjectService;
import by.ormedia.vote.service.IUserService;
import by.ormedia.vote.service.IVoteTicketService;
import by.ormedia.vote.util.JSONUtils;

@RestController
@RequestMapping("/")
public class Controller {
	
	public static final String GET_SUBJECT_INFO = "/getsubjectinfo";
	public static final String VOTE_LINK = "/votelink";
	public static final String CLOSE_VOTE = "/close";
	public static final String URL = "env-2982050.mycloud.by/";
	public static final String VOTE_TICKET_ID = "voteid";
	public static final String ITEM_ID = "item";
	public static final String KEY_CODE = "keycode";
	
	@Autowired
	private IMailSender mailSender;
	
	@Autowired
	private ISubjectService subjectService;
	
	@Autowired
	private IVoteTicketService voteTicketService;
	
	@Autowired
	private IItemService itemService;
	
	@PostMapping("/newsubject")
	public void test(@RequestBody String body){
		Subject subject = JSONUtils.getSubjectFromJSON(body);
		subject.setOpen(true);
		subjectService.addSubject(subject);
		mailSender.sendLinks(subject);
		mailSender.respondInitiator(subject);
	}
	
	@GetMapping(VOTE_LINK)
	public String vote(@RequestParam(required = true) long item, @RequestParam(required = true) long voteid, @RequestParam(required = true) String keycode){
		if(this.voteTicketService.vote(voteid, keycode)){
			if(this.itemService.vote(item)){
				return "Спасибо, ваш голос учтён";
			}
		}
		return "Что-то пошло не так...";
	}
	
	@GetMapping(GET_SUBJECT_INFO)
	public String getSubjectInfo(@RequestParam(required = true) long id){
		Subject subject = this.subjectService.getSubjectById(id);
		if(subject==null)return "Такого голосования не существует";
		StringBuilder sb = new StringBuilder();
		sb.append("Тема: "+subject.getSubject());
		sb.append((char)10);
		sb.append((char)13);
		Iterator<Item> it = subject.getItems().iterator();
		while(it.hasNext()){
			Item item = it.next();
			sb.append(item.getText());
			sb.append(" - ");
			sb.append(item.getSum());
			sb.append((char)10);
			sb.append((char)13);
		}
		return sb.toString();
	}
	
	@GetMapping(CLOSE_VOTE)
	public String closeVote(@RequestParam(required = true) long subjectid, @RequestParam(required = true)long userid){
		Subject subject = this.subjectService.getSubjectById(subjectid);
		if(subject==null)return "Такого голосования не существует";
		User user = subject.getInitiator();
		if(user.getId()!=userid) return "Вы не имеете права на это действие";
		if(!subject.isOpen())return "Голосование уже закрыто";
		subject.setOpen(false);
		if(this.subjectService.updateSubject(subject)){
			return "Голосование успешно закрыто";
		}else return "Что-то пошло не так...";
	}

}
