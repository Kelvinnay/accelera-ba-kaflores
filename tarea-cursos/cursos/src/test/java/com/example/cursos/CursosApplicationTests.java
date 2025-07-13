package com.example.cursos;
/*
import com.example.cursos.repository.CourseRepository;
import org.junit.jupiter.api.Test;
// import org.junit.platform.engine.TestExecutionResult; // This import is not needed for this test
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.bean.override.mockito.MockitoBean; // This import is not needed unless you're mocking a bean
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Corrected import
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc // anotacion para la configuracion por defecto
//@SpringBootTest // solo funciona para pruebas bd embebidas audno llama a BD
class CursosApplicationTests {

	@Autowired
	MockMvc mockMvc; //Crea un objeto que me sirve como servlet de pruebas

	@Test
	void contextLoads() throws Exception { // Add throws Exception because perform() can throw it
		//ARRANGE
		// RequestBuilder rb = get("/api/v1/main"); // You can define it like this, but it's often inlined.

		//ACT & ASSERT (chained)
		mockMvc.perform(get("/api/v1/main"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$").isArray()); // Assert the first element


		// If you had more assertions that depend on the response, they would go here.
		// For example:
		// MvcResult result = mockMvc.perform(get("/api/v1/main")).andReturn();
		// String content = result.getResponse().getContentAsString();
		// System.out.println(content); // For debugging
	}

	@Test
	void test_saveUser()throws Exception {

		mockMvc.perform(post("/api/v1/main")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
							"    \"name\": \"andrea\",\n" +
							"    \"email\": \"andrea@gmail.com\",\n" +
							"    \"userType\": \"STUDENT\"\n" +
							"}"))
				.andExpect(status().isOk());

	}
}

 */
