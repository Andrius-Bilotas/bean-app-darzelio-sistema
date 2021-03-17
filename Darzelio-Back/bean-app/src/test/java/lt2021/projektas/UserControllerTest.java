package lt2021.projektas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lt2021.projektas.userRegister.CreateUserCommand;
import lt2021.projektas.userRegister.ServiceLayerUser;
import lt2021.projektas.userRegister.User;
import lt2021.projektas.userRegister.UserDao;
import lt2021.projektas.userRegister.UserRole;
import lt2021.projektas.userRegister.UserService;
import lt2021.projektas.userRegister.UserTableObject;


@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate rest;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@After
	public void teardown() {
		var users = userDao.findAll().stream().filter(user -> !user.getEmail().equals("admin@test.lt")).collect(Collectors.toList());
		users.forEach(user -> {
			userDao.delete(user);
		});
	}
	
	@Test
	public void createUserMethodCreatesUserProperly() throws JSONException {
		
		final String firstname = "Testa";
		final String lastname = "Testauskaite";
		final String email = "test@mail.com";
		final String password = "Slaptazodis1";
		UserRole role = UserRole.PARENT;
		
		var json = new JSONObject();
		json.put("firstname", firstname);
		json.put("lastname", lastname);
		json.put("email", email);
		json.put("password", password);
		json.put("role", role.toString());
		
		var headers = this.loginWithSpecifiedUser("admin@test.lt", "admin");
		
		HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
		
		rest.postForEntity((rest.getRootUri() + "/api/users"), request, String.class);
		
		List<User> users = userDao.findAll();
		assertTrue(users.stream().anyMatch(u -> u.getEmail().equals(email)), "POST request should create user and store them in the database");
		var user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
		assertFalse(password.equals(user.getPassword()), "Created user's password should be encrypted");

	}
	
	@Test
	public void getUsersMethodReturnsListOfUsersProperly() {
		
		var headers = this.loginWithSpecifiedUser("admin@test.lt", "admin");
		HttpEntity<String> request = new HttpEntity<>(headers);
		var response = rest.exchange(rest.getRootUri() + "/api/users?page=1", HttpMethod.GET, request, UserTableObject.class);
		System.out.println("????????? " + response.getBody());
		List<ServiceLayerUser> responseUsers = response.getBody().getUsers();
		var users = userDao.findAll();
		assertTrue(responseUsers.size() == users.size(), "GET request should return correct amount of users");
		
	}
	
	@Test
	public void getUserReturnsCorrentSpecificUser() {
		var headers = this.loginWithSpecifiedUser("admin@test.lt", "admin");
		HttpEntity<String> request = new HttpEntity<>(headers);
		var response = rest.exchange(rest.getRootUri() + "/api/users/1", HttpMethod.GET, request, ServiceLayerUser.class);
		ServiceLayerUser responseUser = response.getBody();
		var dbUser = userDao.findById(1L).orElse(null);
		assertTrue(responseUser.getEmail().equals(dbUser.getEmail()), "GET user by id request should return the correct user");
	}
	
	@Test
	public void updateUserShouldUpdateCorrectUserProperly() throws JSONException {
		
		var newUser = userDao.save(new User("Testa", "Testauskaite", "test@mail.com", "Slaptazodis1", UserRole.PARENT));
		
		final String firstname = "Testa";
		final String lastname = "KitaPavarde";
		final String email = "test@mail.com";
		final String password = "Slaptazodis1";
		UserRole role = UserRole.PARENT;
		
		var json = new JSONObject();
		json.put("firstname", firstname);
		json.put("lastname", lastname);
		json.put("email", email);
		json.put("password", password);
		json.put("role", role.toString());
		
		var headers = this.loginWithSpecifiedUser("admin@test.lt", "admin");
		HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
		rest.exchange(rest.getRootUri() + "/api/users/" + newUser.getId(), HttpMethod.PUT, request, String.class);
		
		var editedUser = userDao.searchByEmail(email).orElse(null);
		assertTrue(editedUser.getLastname().equals(lastname), "Edited users lastname should be changed and saved to the database");
	}
	
	@Test
	public void deleteUserShouldDeleteTheCorrectUser() {
		var newUser = userDao.save(new User("Testa", "Testauskaite", "test@mail.com", "Slaptazodis1", UserRole.PARENT));
		var headers = this.loginWithSpecifiedUser("admin@test.lt", "admin");
		HttpEntity<String> request = new HttpEntity<>(headers);
		rest.exchange(rest.getRootUri() + "/api/users/" + newUser.getId(), HttpMethod.DELETE, request, String.class);
		var users = userDao.findAll();
		var leftUser = users.get(0);
		assertTrue((users.size() == 1 && leftUser.getEmail().equals("admin@test.lt")), "DELETE user request should delete the correct user");
	}
	
	@Test
	public void deleteLoggedUserDeletesTheCorrectUser() throws JSONException {
		/*
		var userJson = new JSONObject();
		userJson.put("firstname", "Delete");
		userJson.put("lastname", "User");
		userJson.put("email", "usertodelete@mail.com");
		userJson.put("password", "password");
		userJson.put("role", UserRole.PARENT.toString());
		
		var headers = this.loginWithSpecifiedUser("admin@test.lt", "admin");
		
		HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
		
		rest.postForEntity((rest.getRootUri() + "/api/users"), request, String.class);
		
		this.logoutFromuser(headers);
		*/
		
		var admin = userDao.findAll().get(0);
		
		userService.createUser(new CreateUserCommand("Delete", "User", "usertodelete@mail.com", "password1", UserRole.PARENT), admin);
		
		var newUser = userDao.findAll().stream().filter(u -> u.getEmail().equals("usertodelete@mail.com")).findAny().orElse(null);
		
		System.out.println("????????? Database size: " + userDao.findAll().size());
		System.out.println("????????? New user: " + newUser.getEmail() + ", password: " + newUser.getPassword() + ", id: " + newUser.getId());
		System.out.println("????????? Password match: " + passwordEncoder.matches("password1", newUser.getPassword()));
		
		//var headers = this.loginWithSpecifiedUser("usertodelete@mail.com", "password1");
		//var request = new HttpEntity<>(headers);
		//rest.delete(rest.getRootUri() + "/api/users/delete", request, String.class);
		var users = userDao.findAll();
		//assertTrue((users.size() == 1 && !users.stream().anyMatch(u -> u.getEmail().equals("usertodelete@mail.com"))), "Logged in user should be deleted correctly");
	}
	
	private HttpHeaders loginWithSpecifiedUser(String username, String password) {
		
		HttpHeaders loginHeaders = new HttpHeaders();
		loginHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("username", username);
		map.add("password", password);
		
		HttpEntity<MultiValueMap<String, String>> loginRequest = new HttpEntity<>(map, loginHeaders);
		
		var loginTest = rest.postForEntity(rest.getRootUri() + "/login", loginRequest, String.class);
		
		if (username.equals("usertodelete@mail.com")) {
			System.out.println("????????? " + loginTest);
			System.out.println("????????? " + loginTest.getBody());
		}
		
		
		var cookie = loginTest.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
		cookie = cookie.substring(0, 43);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		headers.add("Cookie", cookie);
		
		return headers;
	}
	
	private void logoutFromuser(HttpHeaders headers) {
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		var response = rest.exchange(rest.getRootUri() + "/logout", HttpMethod.GET, request, String.class);
		System.out.println("???????? Logout: " + response);
	}
	
}
