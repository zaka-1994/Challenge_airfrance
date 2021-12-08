package com.example.airfrance.challenge.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.airfrance.challenge.dao.UserDao;
import com.example.airfrance.challenge.entities.User;
import com.example.airfrance.challenge.services.UserService;

/**
 * Class for unit tests of user using mocking
 * 
 * @author zhaimi
 * @Date 05/12/2021
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ChallengeAirFranceUnitTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserDao userDao;

	/**
	 * Test service register user using mocking
	 */
	@Test
	public void registerUserTest() {
		User expectedUser = new User(999, "Paul", LocalDate.of(1975, 3, 10), "France");
		when(userDao.save(expectedUser)).thenReturn(expectedUser);
		User actualUser = userService.registerUser(expectedUser);
		assertEquals(expectedUser, actualUser);
	}
	/**
	 * Test service register user using mocking fail
	 */
	@Test
	public void registerUserTestKO() {
		User expectedUser = new User(99999, "Paul", LocalDate.of(1975, 3, 10), "Autriche");
		when(userDao.save(expectedUser)).thenReturn(null);
		User actualUser = userService.registerUser(expectedUser);
		assertEquals(null, actualUser);
	}

	/**
	 * Test service get user details using mocking
	 */
	@Test
	public void getUserDetailsByIdTest() {
		User user = new User(998, "Claudia", LocalDate.of(1999, 5, 7), "France");
		when(userDao.findById(998)).thenReturn(Optional.of(user));
		assertEquals(Optional.of(user), userService.getUserDetailsById(998));
	}

	/**
	 * Test service get user details KO using mocking
	 */
	@Test
	public void getUserDetailsByIdTestKO() {
		when(userDao.findById(9999999)).thenReturn(null);
		assertEquals(Optional.empty(), userService.getUserDetailsById(998));
	}

}
