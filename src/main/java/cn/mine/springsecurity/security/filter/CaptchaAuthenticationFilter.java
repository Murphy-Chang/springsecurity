package cn.mine.springsecurity.security.filter;

import cn.mine.springsecurity.security.service.CaptchaAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Title:
 * Description:springsecurity自定义验证码过滤器
 * Copyright: Copyright (c) 2018
 * Company: MINE
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/6/19
 */
public class CaptchaAuthenticationFilter extends GenericFilterBean {
	//验证码拦截路径
	private static final String CODE_ANT_URL = "/login";
	private boolean postOnly = true;

	//请求路径匹配
	private RequestMatcher requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(CODE_ANT_URL, "POST");

	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/web/login/login.html?unverified"); //设置验证失败重定向路径

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 不是需要过滤的路径，执行下一个过滤器
		if (!requiresAuthentication(request, response)) {
			filterChain.doFilter(request, response);
			return;
		}

		Authentication authResult;
		try {
			authResult = this.attemptAuthentication(request, response);
			if (authResult == null) {
				return;
			}

		} catch (InternalAuthenticationServiceException failed) {
			return;
		} catch (AuthenticationException failed) {
			//Authentication failed
			unsuccessfulAuthentication(request, response, failed);
			return;
		}

		//认证成功，执行下个过滤器
		filterChain.doFilter(request, response);
	}

	private Authentication attemptAuthentication(HttpServletRequest request,
												 HttpServletResponse response) throws AuthenticationException {
		//只允许post请求
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		//验证码通过标识 0未通过 1通过
		int result = 0;

		//后台生成验证码
		Object trueCaptcha = request.getSession().getAttribute("captcha");
		//用户填写验证码
		String userCaptcha = request.getParameter("captcha");

		//判断验证码都不为空
		if (trueCaptcha != null && !StringUtils.isEmpty(userCaptcha)) {
			//判断验证码是否相等
			if(userCaptcha.equalsIgnoreCase(trueCaptcha.toString())){
				result = 1;
			}
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(result==1?"yes":"no", null);

		return new CaptchaAuthenticationProvider().authenticate(authRequest);
	}

	/**
	 * 比较需要过滤的请求路径
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	protected boolean requiresAuthentication(HttpServletRequest request,
											 HttpServletResponse response) {
		return requiresAuthenticationRequestMatcher.matches(request);
	}

	/**
	 * 处理验证码认证失败
	 * 参考 {@link org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter}
	 * Default behaviour for unsuccessful authentication.
	 * <ol>
	 * <li>Clears the {@link SecurityContextHolder}</li>
	 * <li>Stores the exception in the session (if it exists or
	 * <tt>allowSesssionCreation</tt> is set to <tt>true</tt>)</li>
	 * <li>Informs the configured <tt>RememberMeServices</tt> of the failed login</li>
	 * <li>Delegates additional behaviour to the {@link AuthenticationFailureHandler}.</li>
	 * </ol>
	 */
	protected void unsuccessfulAuthentication(HttpServletRequest request,
											  HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		SecurityContextHolder.clearContext();

		failureHandler.onAuthenticationFailure(request, response, failed);
	}

}
