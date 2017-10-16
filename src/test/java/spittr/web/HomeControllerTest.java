package spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.Spittle;
import spittr.data.SpittleRepository;

public class HomeControllerTest {

	@Test
	public void homeControllerTest() throws Exception {
		HomeController controller = new HomeController();
		// 搭建MockMvc
		MockMvc mockMvc = standaloneSetup(controller).build();
		// 对“/”执行GET请求
		mockMvc.perform(get("/")).andExpect(view().name("home"));// 预期得到home视图
	}

	@Test
	public void shoudShowRecentSpittles() {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		
	}

	private List<Spittle> createSpittleList(int i) {
		return null;
	}
}
