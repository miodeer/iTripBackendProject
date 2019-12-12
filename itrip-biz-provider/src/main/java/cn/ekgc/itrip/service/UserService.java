package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.User;

/**
 * 用户业务层
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
public interface UserService {
	/**
	 * <b>通过userCode查询User对象</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	User getUserByUserCode(String userCode) throws Exception;
}
