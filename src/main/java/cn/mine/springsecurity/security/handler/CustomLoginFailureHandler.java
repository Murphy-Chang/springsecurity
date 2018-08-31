package cn.mine.springsecurity.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败管理器
 * @author Murphy.Chang
 *
 */
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	/**
	 * 登录失败的回调
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		super.setDefaultFailureUrl("/web/login/login.html?error");
		super.onAuthenticationFailure(request, response, exception);
	}
}
