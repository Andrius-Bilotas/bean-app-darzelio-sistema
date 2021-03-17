package lt2021.projektas;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;

import lt2021.projektas.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {
	@Value("${local.server.port}")
	int port;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = port;
	}

	@Test
	public void testHello() throws Exception {
	}

//	@Test
//	public void testCalc() throws Exception {
//		
//		given().param("left", 100).param("right", 200).get("/calc").then().body("left", is(100)).body("right", is(200))
//				.body("answer", is(300));
//	}
}