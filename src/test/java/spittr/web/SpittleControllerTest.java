package spittr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.View;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;

public class SpittleControllerTest {

	@Test
	public void shoudShowPagedSpittles() throws Exception {
		List<Spittle> expectedSpittles = creatSpittleList(50);
		SpittleRepository mockRespository = Mockito.mock(SpittleRepository.class);
		// 预期的max和count的值
		Mockito.when(mockRespository.findSpittles(238900, 50)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRespository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spttles.jsp")).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spttles?max=238900&count=50"))
				.andExpect(MockMvcResultMatchers.view().name("spttles"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("spttleList")).andExpect(MockMvcResultMatchers
						.model().attribute("spttleList", Matchers.hasItems(expectedSpittles.toArray())));
	}

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = creatSpittleList(20);
		// Mock Repository
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		Mockito.when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spttles.jsp")).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spttles")).andExpect(MockMvcResultMatchers.view().name("spttles"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("spttleList")).andExpect(MockMvcResultMatchers
						.model().attribute("spttleList", Matchers.hasItems(expectedSpittles.toArray())));

	}

	private List<Spittle> creatSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spttle " + i, new Date()));
		}
		return spittles;
	}
}
