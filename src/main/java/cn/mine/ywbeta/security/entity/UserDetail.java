package cn.mine.ywbeta.security.entity;

import cn.mine.ywbeta.jpa.entity.TUserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * security用户类
 * @author Murphy.Chang
 */
public class UserDetail implements UserDetails {
	private static final long serialVersionUID = 4769294440918131756L;
	private int userId;
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();//对应的角色权限

	/**
	 * 构造方法
	 * @param userInfo
	 */
	public UserDetail(TUserInfo userInfo){
		this.userId = userInfo.getUserId();
		this.userName = userInfo.getUserName();
		this.password = userInfo.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return this.userName;
	}
	
	@Override
	public int hashCode() {
		return this.userName.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
}
