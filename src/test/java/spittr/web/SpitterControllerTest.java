package spittr.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import spittr.Spitter;
import spittr.data.SpitterRepository;

public class SpitterControllerTest {
	@Test
	public void shouldShowRegistration() throws Exception {

		SpitterRepository mRepository = Mockito.mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(mRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();// 构建MockMvc

		mockMvc.perform(MockMvcRequestBuilders.get("/spitter/register"))
				.andExpect(MockMvcResultMatchers.view().name("registerForm"));// 断言registerForm视图
	}

	@Test
	public void shouldProcessRepositoration() throws Exception {
		SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "000", "Jack", "Bauer");
		Spitter saved = new Spitter(24L, "jbauer", "000", "Jack", "Bauer");

		Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);

		SpitterController controller = new SpitterController(mockRepository);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/spitter/register").param("firstName","Jack").param("lastName", "Bauer").param("username", "jbauer").param("password", "000")).andExpect(MockMvcResultMatchers.redirectedUrl("/spitter/jbauer"));

	}
}
