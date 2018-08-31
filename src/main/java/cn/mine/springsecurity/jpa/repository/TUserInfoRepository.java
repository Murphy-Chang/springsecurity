package cn.mine.springsecurity.jpa.repository;

import cn.mine.springsecurity.jpa.entity.TUserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

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
public interface TUserInfoRepository extends CrudRepository<TUserInfo, Integer> {
	public List<TUserInfo> getListByUserNameAndIsDelete(String userName, int isDelete);
}
