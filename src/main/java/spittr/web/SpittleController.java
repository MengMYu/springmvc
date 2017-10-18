package spittr.web;

import java.util.List;

import org.junit.experimental.max.MaxCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;
	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	// 注入SpittleRepository
	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		// 将spittle添加到模型中
		model.addAllAttributes(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		// 返回视图名
		return "spittles";
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
	}
}
