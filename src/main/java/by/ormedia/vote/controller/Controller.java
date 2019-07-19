package by.ormedia.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.service.ISubjectService;
import by.ormedia.vote.service.IUserService;
import by.ormedia.vote.util.JSONUtils;

@RestController
@RequestMapping("/")
public class Controller {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/newsubject")
	public void test(@RequestBody String body){
		Subject subject = JSONUtils.getSubjectFromJSON(body);
		System.out.println(subject);		
	}

}
