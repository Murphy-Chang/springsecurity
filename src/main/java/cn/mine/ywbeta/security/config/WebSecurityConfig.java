package cn.mine.ywbeta.security.config;

import cn.mine.ywbeta.security.filter.CaptchaAuthenticationFilter;
import cn.mine.ywbeta.security.handler.CustomLoginFailureHandler;
import cn.mine.ywbeta.security.handler.CustomLoginSuccessHandler;
import cn.mine.ywbeta.security.handler.CustomLogoutSuccessHandler;
import cn.mine.ywbeta.security.service.CustomAccessDecisionManager;
import cn.mine.ywbeta.security.service.CustomJdbcTokenRepositoryImpl;
import cn.mine.ywbeta.security.service.CustomUserService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * spring-security配置
 * @author Murphy.Chang
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	/**
     * springsecurity配置
     * @author Murphy.Chang
     * @return
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()//拦截页面
			.accessDecisionManager(customAccessDecisionManager())//使用自定义授权管理器
	        .anyRequest().authenticated();//全部页面都要验证

    	//禁用csrf
    	http.csrf().disable();

    	//添加自定义验证码过滤器
    	http.addFilterBefore(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    	//登录的相应设置
    	http.formLogin()
    	 	.loginPage("/web/login/login.html")//指定登录页面
    	 	.loginProcessingUrl("/login")//指定登录接口
			.successHandler(authenticationSuccessHandler())//注入登录成功管理器
			.failureHandler(authenticationFailureHandler())//注入登录失败管理器
			.permitAll();
    	
    	http.rememberMe()//开启持久化登录功能
			.rememberMeParameter("remember-me")//设置持久化登录参数名称
			.userDetailsService(customUserService())//注入自定义用户源
			.authenticationSuccessHandler(authenticationSuccessHandler())//注入登录成功管理器
			.key("ywbeta")//持久化登录功能的加密加盐
			.tokenRepository(customJdbcTokenRepositoryImpl())//设置持久化存储token的repository
			.tokenValiditySeconds(1000*60*60*24*7);//设置持久化登录时长
    	
    	//注销的相应设置
		http.logout()
			.logoutUrl("/logout")//指定注销接口
			.logoutSuccessHandler(logoutSuccessHandler())//注入注销成功管理器
			.deleteCookies("remeber-me")//删除cookie
			.permitAll();
		
		http.sessionManagement()//Session管理器
			.sessionFixation()
			.changeSessionId()//改变sessionId策略
	        .invalidSessionUrl("/web/login/login.html?invalid")//Session超时跳转路径
	        .maximumSessions(1)//只能同时一个人在线
	        .expiredUrl("/web/login/login.html?expired");//其他人登录
		
		http.exceptionHandling()//权限验证失败进入的页面（只对使用自定义拦截有效）
			.accessDeniedPage("/web/common/403.html");//权限验证失败跳转路径
	
	    http.headers()//允许同源iframe访问
	        .frameOptions()
	        .sameOrigin();
    }

    /**
     * Web层面的拦截，用来跳过的资源
     * @author Murphy.Chang
     * @return
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    	//静态资源
    	web.ignoring().antMatchers("/static/**");//静态资源
    	web.ignoring().antMatchers("/**/favicon.ico");//网站图标
    	//url
    	web.ignoring().antMatchers("/");//首页跳转url
    	web.ignoring().antMatchers("/web/common/**");//公共接口url
    	web.ignoring().antMatchers("/web/login/**");//登录相关url
    }
    
    /**
     * 用户认证管理器
     * @author Murphy.Chang
     * @return
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(new ShaPasswordEncoder());
    }

	/**
	 * 自定义验证码过滤器
	 * @return
	 */
	@Bean
	public CaptchaAuthenticationFilter captchaAuthenticationFilter(){
		return new CaptchaAuthenticationFilter();
	}

	/**
	 * 决策管理器
	 * @author Murphy.Chang
	 * @return
	 */
	@Bean
	public CustomAccessDecisionManager customAccessDecisionManager() {
		return new CustomAccessDecisionManager();
	}
	
	/**
     * 用户授权认证方法
     * @author Murphy.Chang
     * @return
     */
    @Bean
    public CustomUserService customUserService() {
    	return new CustomUserService();
    }
    
	/**
	 * 登录成功管理器
	 * @author Murphy.Chang
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler(){
		return new CustomLoginSuccessHandler();
	}
    
    /**
     * 登录失败处理
     * @author Murphy.Chang
     * @return
     */
    @Bean
	public AuthenticationFailureHandler authenticationFailureHandler(){
		return new CustomLoginFailureHandler();
	}
    
    /**
     * 注销成功处理
     * @author Murphy.Chang
     * @return
     */
    @Bean
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new CustomLogoutSuccessHandler();
	}
    
	/**
	 * 持久化存储token的repository
	 * @author Murphy.Chang
	 * @return
	 */
	@Bean
	protected CustomJdbcTokenRepositoryImpl customJdbcTokenRepositoryImpl(){
		CustomJdbcTokenRepositoryImpl customJdbcTokenRepositoryImpl = new CustomJdbcTokenRepositoryImpl();
		customJdbcTokenRepositoryImpl.setDataSource(dataSource);
		return customJdbcTokenRepositoryImpl;
	}
}
