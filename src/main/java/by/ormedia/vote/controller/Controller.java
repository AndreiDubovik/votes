package by.ormedia.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.ormedia.vote.entity.Subject;
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
	public static final String URL = "localhost:8080";
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

}
