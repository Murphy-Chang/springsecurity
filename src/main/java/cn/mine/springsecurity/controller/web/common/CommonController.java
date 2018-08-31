package cn.mine.springsecurity.controller.web.common;

import cn.mine.springsecurity.util.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;

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
@RequestMapping(value = "/web/common")
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	/**
	 * 接口--获取图片验证码
	 * @author Murphy.Chang
	 * @param session
	 * @param response
	 */
	@RequestMapping("/getCaptcha")
	public void getCaptcha(HttpSession session, HttpServletResponse response) {
		logger.info("接口--获取图片验证码");
		try {
			//设置页面不缓存
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String captcha = CaptchaUtil.generateTextCode(CaptchaUtil.TYPE_NUM_UPPER, 4, null);
			session.setAttribute("captcha", captcha);
			logger.info("本次生成的验证码为:{} sessionID:{}", captcha, session.getId());
			//设置输出的内容的类型为JPEG图像
			response.setContentType("image/jpeg");
			BufferedImage bufferedImage = CaptchaUtil.generateImageCode(captcha, 90, 30, 5, true, Color.WHITE, null, null);
			//写给浏览器
			ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			logger.info("接口--获取图片验证码出错，msg:{}", e.getMessage());
		}
	}

	/**
	 * 跳转--403页面
	 * @author Murphy.Chang
	 * @return
	 */
	@RequestMapping(value = "/403.html", method = {RequestMethod.POST, RequestMethod.GET})
	public String error403(){
		logger.info("跳转--403页面");
		try{
			return "/error/403";
		}catch (Exception e){
			logger.info("跳转--403页面出错, msg:{}", e.getMessage());
			return "/error/500";
		}
	}

}