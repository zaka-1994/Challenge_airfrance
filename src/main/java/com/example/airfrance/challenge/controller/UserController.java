package com.example.airfrance.challenge.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airfrance.challenge.entities.User;
import com.example.airfrance.challenge.services.UserService;

/**
 * User Controller to manage registration and recovering for a user
 * @author zhaimi
 * @Date 05/12/2021 
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	// dependency injection of user service
	@Autowired
	private UserService userService;

	
	/**
	 * This method call method register of the user Service to 
	 * save user into the database H2
	 * @param a valid user 
	 * @return a http status created if it was created and an exception if not
	 */
	@PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
		try {
			User _user = userService.registerUser(user);
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method recover an user from his id
	 * @param idUser ( must be an integer )
	 * @return This method return a ResponseEntity with the user and a http status OK
	 * (200) and an exception if the user doesn't exist
	 */
	@GetMapping(value = "/details/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUserDetailsById(@PathVariable("userId") Integer idUser) {
		Optional<User> user = Optional.of(userService.getUserDetailsById(idUser)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+ idUser)));

		return new ResponseEntity<>(user.get(), HttpStatus.OK);

	}

}
