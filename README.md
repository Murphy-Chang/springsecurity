# springsecurity
项目结构\
springboot 1.5.13.RELEASE\
gradle 3.4.1\
jpa\
mysql\
springsecurity\
thymeleaf

本demo主要编写了springsecurity的用法

登录注销功能\
没什么好说的，主要添加了验证码验证功能，通过添加自定义过滤器CaptchaAuthenticationFilter和自定义验证方法CaptchaAuthenticationProvider，实现了添加验证码功能，与session管理完美结合不冲突

持久化登录\
自定义持久化存储token的repository----CustomJdbcTokenRepositoryImpl

权限管理\
重写了用户权限类CustomGrantedAuthority
重写了中央授权管理器CustomAccessDecisionManager(本demo暂时未作任何权限控制，仅实现了功能)

session管理器\
session超时管理\
仅允许一人登录

多个回调方法类\
登录成功回调CustomLoginSuccessHandler\
登录失败回调CustomLoginFailureHandler\
注销成功回调CustomLogoutSuccessHandler

sql文件再resources下
别忘记建立database(本demo的database名为mine)哦

欢迎交流和指正错误\
@author Murphy.Chang



