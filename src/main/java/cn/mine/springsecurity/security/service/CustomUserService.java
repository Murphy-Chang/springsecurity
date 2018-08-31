package cn.mine.springsecurity.security.service;

import cn.mine.springsecurity.jpa.entity.TUserInfo;
import cn.mine.springsecurity.jpa.repository.TUserInfoRepository;
import cn.mine.springsecurity.security.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * 身份认证
 * @author Murphy.Chang
 *
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    private TUserInfoRepository userInfoRepo;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	//从数据库中找到用户
        List<TUserInfo> userInfoList = userInfoRepo.getListByUserNameAndIsDelete(s, 0);
        if (userInfoList == null || userInfoList.size() == 0) {
        	throw new UsernameNotFoundException("用户名不存在");
        }
        if(userInfoList.size() > 1){
        	throw new UsernameNotFoundException("有重复用户存在");
        }

        return new UserDetail(userInfoList.get(0));
    }
}
