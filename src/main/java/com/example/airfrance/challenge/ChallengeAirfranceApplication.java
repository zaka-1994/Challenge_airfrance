package com.example.airfrance.challenge;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.airfrance.challenge.entities.User;
import com.example.airfrance.challenge.services.UserService;

@SpringBootApplication
public class ChallengeAirfranceApplication implements CommandLineRunner{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ChallengeAirfranceApplication.class, args);
	}
	@Autowired
	private UserService service;
	
	@Override
	public void run(String... args) throws Exception {
		service.registerUser(new User(1, "Zakaria", LocalDate.of(1994, 4, 9), "France"));
		
	}

}
