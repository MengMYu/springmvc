package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//声明一个控制器
@Controller
@RequestMapping({ "/", "/homepage" })
public class HomeController {
	// 处理对"/"的GET请求
	@RequestMapping(method = GET)
	public String home() {
		// 视图名
		return "home";
	}

}
