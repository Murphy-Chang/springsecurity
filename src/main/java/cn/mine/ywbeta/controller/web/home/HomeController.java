package cn.mine.ywbeta.controller.web.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 * Company: MINE
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/5/29
 */
@Controller
@RequestMapping(value = "/web/home")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * 跳转--首页
	 * @author Murphy.Chang
	 * @return
	 */
	@RequestMapping(value = "/home.html", method = {RequestMethod.POST, RequestMethod.GET})
	public String home(){
		logger.info("跳转--首页");
		try{
			return "/home/home";
		}catch (Exception e){
			logger.info("跳转--首页出错, msg:{}", e.getMessage());
			return "/error/500";
		}
	}

}