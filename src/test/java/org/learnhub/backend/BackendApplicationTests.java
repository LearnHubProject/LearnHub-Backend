package org.learnhub.backend;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.learnhub.backend.request.TestLoginRequest;
import org.learnhub.backend.response.TestLoginResponse;
import org.learnhub.backend.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb",
		"spring.datasource.username=test",
		"spring.datasource.password=",
		"spring.datasource.driverClassName=org.h2.Driver"
})
@AutoConfigureMockMvc
class BackendApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	Environment env;

	@Autowired
	JWTUtils jwtUtils;

	@Value("${DOMAIN}")
	String domain;

	private String adminToken;


	@Order(0)
	@Test
	void loginAsAdmin() throws Exception{

		TestLoginRequest loginRequest = new TestLoginRequest("admin@"+domain, "changeme");

		ObjectMapper mapper = new ObjectMapper();

		String jsonPayload = mapper.writeValueAsString(loginRequest);

		MockHttpServletResponse result = mockMvc.perform(MockMvcRequestBuilders.post("/api/login").contentType(MediaType.APPLICATION_JSON).content(jsonPayload)).andReturn().getResponse();

		assertEquals("Error code is different from 200", 200, result.getStatus());

		TestLoginResponse response = mapper.readValue(result.getContentAsString(), TestLoginResponse.class);

		adminToken = mapper.readValue(result.getContentAsString(), TestLoginResponse.class).getToken();
		assertNotNull("JWT token is invalid", adminToken);
	}

	@Order(1)
	@Test
	void loginAsAdminWithWrongCredentials() throws Exception{

		//wrong email
		TestLoginRequest loginRequestWrongEmail = new TestLoginRequest("admin@"+domain+".definitely-wrong", "changeme");
		TestLoginRequest loginRequestWrongPass = new TestLoginRequest("admin@"+domain, "changeme.definitely-wrong");

		List<TestLoginRequest> requests = new ArrayList<TestLoginRequest>();
		requests.add(loginRequestWrongEmail);
		requests.add(loginRequestWrongPass);

		for(TestLoginRequest loginRequest : requests){
			ObjectMapper mapper = new ObjectMapper();

			String jsonPayload = mapper.writeValueAsString(loginRequest);
			MockHttpServletResponse result = mockMvc.perform(MockMvcRequestBuilders.post("/api/login").contentType(MediaType.APPLICATION_JSON).content(jsonPayload)).andReturn().getResponse();

			assertEquals("Code was not 403 Forbidden", 403, result.getStatus());
		}
	}

	@Order(2)
	@Test
	void createTestSchool() throws Exception{

	}

	@Test
	void dummy2(){
		System.out.println("hola!");
		return;
	}

}
