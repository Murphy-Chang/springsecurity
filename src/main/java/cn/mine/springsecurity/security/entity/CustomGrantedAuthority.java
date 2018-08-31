package cn.mine.springsecurity.security.entity;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = -5783700548293462276L;
	private String authority;
	
	public CustomGrantedAuthority(String authority){
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
