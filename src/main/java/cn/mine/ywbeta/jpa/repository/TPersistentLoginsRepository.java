package cn.mine.ywbeta.jpa.repository;

import cn.mine.ywbeta.jpa.entity.TPersistentLogins;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * ClassName: PersistentLoginsRepository
 * Function: TODO ADD FUNCTION.
 * date: 2017年11月28日 下午5:42:07
 *
 * @author Murphy.Chang
 * @version
 */
public interface TPersistentLoginsRepository extends CrudRepository<TPersistentLogins, Integer> {
	public void deleteListByUserName(String userName);
	
	public List<TPersistentLogins> getListBySeries(String series);
}
