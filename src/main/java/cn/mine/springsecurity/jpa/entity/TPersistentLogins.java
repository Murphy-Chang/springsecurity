package cn.mine.springsecurity.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the t_persistent_logins database table.
 * 
 */
@Entity
@Table(name="t_persistent_logins")
public class TPersistentLogins implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="series")
	private String series;

	@Column(name="user_name")
	private String userName;

	@Column(name="token")
	private String token;

	@Column(name="last_used")
	private String lastUsed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(String lastUsed) {
		this.lastUsed = lastUsed;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}
}