package spittr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.taglibs.standard.tag.common.xml.WhenTag;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;
import spittr.web.SpittleController;

public class SpittleControllerTest {

	@Test
	public void shoudShowPagedSpittles() throws Exception {
		List<Spittle> expectedSpittles = creatSpittleList(50);
		SpittleRepository mockRespository = Mockito.mock(SpittleRepository.class);
		// 预期的max和count的值.
		Mockito.when(mockRespository.findSpittles(238900, 50)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRespository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=238900&count=50"))
				.andExpect(MockMvcResultMatchers.view().name("spittles"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList")).andExpect(MockMvcResultMatchers
						.model().attribute("spittleList", Matchers.hasItems(expectedSpittles.toArray())));
	}

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = creatSpittleList(20);
		// Mock Repository
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		Mockito.when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();

		mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
				.andExpect(MockMvcResultMatchers.view().name("spittles"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList")).andExpect(MockMvcResultMatchers
						.model().attribute("spittleList", Matchers.hasItems(expectedSpittles.toArray())));

	}

	@Test
	public void testSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle("hello", new Date());
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		Mockito.when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);

		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		// 通过路径请求资源
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles/12345"))
				.andExpect(MockMvcResultMatchers.view().name("spittle"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
				.andExpect(MockMvcResultMatchers.model().attribute("spittle", expectedSpittle));
	}

	private List<Spittle> creatSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
}
