package cn.mine.ywbeta.security.service;

import cn.mine.ywbeta.jpa.entity.TPersistentLogins;
import cn.mine.ywbeta.jpa.repository.TPersistentLoginsRepository;
import cn.mine.ywbeta.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * 持久化存储token的repository
 * @author Murphy.Chang
 */
public class CustomJdbcTokenRepositoryImpl extends JdbcTokenRepositoryImpl {
	// ~ autowired
	// =====================================================================================
	@Autowired
	private TPersistentLoginsRepository persistentLoginsRepository;

	@Override
	protected void initDao() {
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		//新建
		TPersistentLogins entity = new TPersistentLogins();
		entity.setSeries(token.getSeries());
		entity.setUserName(token.getUsername());
		entity.setToken(token.getTokenValue());
		entity.setLastUsed(DateUtil.formatDate1(token.getDate()));
		persistentLoginsRepository.save(entity);
	}

	@Override
	@Transactional
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		//更新
		TPersistentLogins entity = persistentLoginsRepository.getListBySeries(series).get(0);
		entity.setToken(tokenValue);
		entity.setLastUsed(DateUtil.formatDate1(lastUsed));
		persistentLoginsRepository.save(entity);
	}

	/**
	 * Loads the token data for the supplied series identifier.
	 *
	 * If an error occurs, it will be reported and null will be returned (since the result
	 * should just be a failed persistent login).
	 *
	 * @param seriesId
	 * @return the token matching the series, or null if no match found or an exception
	 * occurred.
	 */
	@Override
	public PersistentRememberMeToken getTokenForSeries(String series) {
		TPersistentLogins entity = persistentLoginsRepository.getListBySeries(series).get(0);
		return new PersistentRememberMeToken(entity.getUserName(), entity.getSeries(), entity.getToken(), DateUtil.parseDate1(entity.getLastUsed()));
	}

	@Override
	@Transactional
	public void removeUserTokens(String username) {
		//删除
		persistentLoginsRepository.deleteListByUserName(username);
	}

	/**
	 * Intended for convenience in debugging. Will create the persistent_tokens database
	 * table when the class is initialized during the initDao method.
	 *
	 * @param createTableOnStartup set to true to execute the
	 */
	@Override
	public void setCreateTableOnStartup(boolean createTableOnStartup) {
	}
}
