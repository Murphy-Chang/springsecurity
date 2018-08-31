package cn.mine.ywbeta.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Title:
 * Description:springsecurity自定义验证码过滤器验证方法
 * Copyright: Copyright (c) 2018
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/6/19
 */
public class CaptchaAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String captchaIsVerify = token.getPrincipal().toString();

		if(captchaIsVerify.equals("")){
			throw new BadCredentialsException("please verify first!");
		}

		if(!"yes".equals(captchaIsVerify)){
			throw new BadCredentialsException("please verify first!");
		}

		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
