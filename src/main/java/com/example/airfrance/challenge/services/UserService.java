package com.example.airfrance.challenge.services;

import java.sql.SQLException;
import java.util.Optional;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.airfrance.challenge.Log.Logging;
import com.example.airfrance.challenge.dao.UserDao;
import com.example.airfrance.challenge.entities.User;

/**
 * User service to manage registration and recovering for a user
 * 
 * @author zhaimi
 * @Date 05/12/2021
 */
@Service
public class UserService {

	// dependency injection of user dao
	@Autowired
	private UserDao userDao;

	Logger log = LoggerFactory.getLogger(UserService.class);

	/**
	 * This method register a user in the database H2
	 * 
	 * @param user
	 * @return the User created
	 */
	public User registerUser(User user) {
		return userDao.save(user);
	}

	/**
	 * This method return a user from id
	 * 
	 * @param idUser
	 * @return a User
	 */
	public Optional<User> getUserDetailsById(Integer idUser) {
		return userDao.findById(idUser);
	}
}
