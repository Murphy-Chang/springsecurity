package cn.mine.ywbeta.controller.web.login;

import cn.mine.ywbeta.mvc.entity.Response;
import cn.mine.ywbeta.util.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/6/12
 */
@Controller
@RequestMapping(value = "/web/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 跳转--登录页面
	 * @author Murphy.Chang
	 * @return
	 */
	@RequestMapping(value = "/login.html", method = {RequestMethod.POST, RequestMethod.GET})
	public String loginHtml(HttpServletRequest request, Model model){
		logger.info("跳转--登录页面");
		try{
			if(request.getParameter("unverified") != null){
				model.addAttribute("message", "验证码错误！");
			}
			if(request.getParameter("error") != null){
				model.addAttribute("message", "登录名密码错误！");
			}
			if(request.getParameter("expired") != null){
				model.addAttribute("message", "该账号已在其他地方登录！");
			}
			if(request.getParameter("invalid") != null){
				model.addAttribute("message", "当前会话已超时，请重新登录！");
			}
			if(request.getParameter("logout") != null){
				model.addAttribute("message", "注销成功！");
			}
			return "/login/login";
		}catch (Exception e){
			logger.info("跳转--登录页面出错, msg:{}", e.getMessage());
			return "/error/error500";
		}
	}

}
