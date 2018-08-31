package cn.mine.ywbeta.security.service;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 授权管理器
 * @author Murphy.Chang
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
	/**
	 * 授权方法
	 * @author Murphy.Chang
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		//未登录
		if(authentication.getPrincipal() instanceof String) throw new AccessDeniedException("权限不足，禁止访问！");
		
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		String url = request.getRequestURI();
		
		//目前不需要拦截
		if(url != null && !url.equals("")) return;
        throw new AccessDeniedException("您所具有的权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
