package by.ormedia.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.ormedia.vote.service.IUserService;

@RestController
@RequestMapping("/")
public class Controller {
	
	@Autowired
	private IUserService userService;
	
	

}
