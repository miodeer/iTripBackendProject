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
	 * 通过userCode查询User对象
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	User getUserByUserCode(String userCode) throws Exception;

	/**
	 * 存储用户信息user
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean saveUser(User user) throws Exception;

	/**
	 * 激活用户
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	String getActiveCodeByUserCode(String userCode) throws Exception;

	/**
	 * 修改用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean updateUser(User user) throws Exception;

	/**
	 * 使用userCode和userPassword进行用户信息登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	User doLoginUser(String userCode, String userPassword) throws Exception;
}
