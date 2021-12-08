package com.example.airfrance.challenge.integrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.example.airfrance.challenge.ChallengeAirfranceApplication;
import com.example.airfrance.challenge.entities.User;

/**
 * Class for Integration tests of user
 * 
 * @author zhaimi
 * @Date 05/12/2021
 */
@SpringBootTest(classes = ChallengeAirfranceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChallengeAirFranceIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getUrl() {
		return "http://localhost:" + port + "/user";
	}

	HttpHeaders headers = new HttpHeaders();

	/**
	 * Test service register user
	 */
	@Test
	public void testRegisterUser() {
		User user = new User(9997, "Sylvain", LocalDate.of(1980, 3, 21), "France");
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getUrl() + "/register", user, User.class);
		assertEquals(201, postResponse.getStatusCodeValue());
	}

	/**
	 * Test error in service register user
	 */
	@Test
	public void testErrorRegisterUser() {
		User user = new User(9998, "Paul", LocalDate.of(1980, 3, 21), "Maroc");
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getUrl() + "/register", user, User.class);
		assertNotEquals(201, postResponse.getStatusCodeValue());
	}

	/**
	 * Test service get user details
	 * 
	 * @throws JSONException
	 */
	@Test
	public void testGetUserDetailsById() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getUrl() + "/details/1", HttpMethod.GET, entity,
				String.class);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testGetEmployeeNotExist() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getUrl() + "/details/1000000", HttpMethod.GET, entity,
				String.class);
		assertEquals(404, response.getStatusCodeValue());

	}

}
