package cn.mine.springsecurity.mvc.config;

import cn.mine.springsecurity.mvc.interceptor.WebInterceptor;
import cn.mine.springsecurity.mvc.interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * 
	 * 注入拦截器.
	 * @return
	 */
    @Bean
    protected WebInterceptor webInterceptor(){
        return new WebInterceptor();
    }
    
	/**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	String[] classPathResourceLocations = {"classpath:/META-INF/resources/",
									           "classpath:/resources/",
									           "classpath:/static/",
									           "classpath:/public/"};
        registry.addResourceHandler("/**").addResourceLocations(classPathResourceLocations);
    }
    
    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor())
                .addPathPatterns("/web/**")
				.excludePathPatterns();
        super.addInterceptors(registry);
    }
    
    /**
     * 
     * 跨域配置.
     * @see 
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Access-Control-Allow-Origin", "*")
                .allowedMethods("Access-Control-Allow-Methods", "*")
                .allowedOrigins("*");
        super.addCorsMappings(registry);
    }
    
    /**
     * 视图控制器
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/web/home/home.html");
        super.addViewControllers(registry);
    }
}
