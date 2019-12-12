package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户数据层
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
@Repository
public interface UserDao {
	/**
	 * 通过查询条件查询用户信息列表
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<User> findUserByQuery(Map<String, Object> queryMap) throws Exception;
}
