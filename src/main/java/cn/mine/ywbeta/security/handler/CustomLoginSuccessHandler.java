package cn.mine.ywbeta.security.handler;

import cn.mine.ywbeta.security.entity.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录成功管理器
 * @author Murphy.Chang
 *
 */
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	/**
	 * 登录成功的回调
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		UserDetail userDetail = (UserDetail) authentication.getPrincipal();

		HttpSession session = request.getSession();
		session.setAttribute("userDetail", userDetail);

		super.setDefaultTargetUrl("/web/home/home.html");

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
