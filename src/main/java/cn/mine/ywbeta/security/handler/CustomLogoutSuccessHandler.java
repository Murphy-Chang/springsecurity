package cn.mine.ywbeta.security.handler;

import cn.mine.ywbeta.security.entity.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注销成功管理器
 * @author Murphy.Chang
 *
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	/**
	 * 注销成功的回调
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
								Authentication authentication) throws IOException, ServletException {

		super.setDefaultTargetUrl("/web/login/login.html?logout");
		super.onLogoutSuccess(request, response, authentication);
	}
}
