package by.ormedia.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import by.ormedia.vote.util.DatabaseUtil;

@SpringBootApplication
public class VoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
		DatabaseUtil.dataBaseInitialization();
	}

}
