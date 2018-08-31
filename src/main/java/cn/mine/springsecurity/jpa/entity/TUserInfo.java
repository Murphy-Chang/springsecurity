package cn.mine.springsecurity.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 * Company: MINE
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/5/29
 */
@Entity
@Table(name="t_user_info")
public class TUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;

	@Column(name="user_name")
	private String userName;

	@Column(name="password")
	private String password;

	@Column(name="nick_name")
	private String nickName;

	@Column(name="user_desc")
	private String userDesc;

	@Column(name="is_delete")
	private int isDelete;

	@Column(name="create_time")
	private String createTime;

	@Column(name="mend_time")
	private String mendTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMendTime() {
		return mendTime;
	}

	public void setMendTime(String mendTime) {
		this.mendTime = mendTime;
	}
}
