package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.service.UserService;
import cn.ekgc.itrip.transport.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户传输层实现类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
@RestController("userTransport")
@RequestMapping("/user")
public class UserTransportImpl implements UserTransport {
	@Autowired
	private UserService userService;

	/**
	 * 通过userCode查询User对象
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/code", method = RequestMethod.POST)
	public User getUserByUserCode(@RequestParam String userCode) throws Exception {
		return userService.getUserByUserCode(userCode);
	}

	/**
	 * 存储用户信息user
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public boolean saveUser(@RequestBody User user) throws Exception {
		return userService.saveUser(user);
	}

	/**
	 * 激活用户
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activate", method = RequestMethod.POST)
	public String getActiveCodeByUserCode(@RequestParam String userCode) throws Exception {
		return userService.getActiveCodeByUserCode(userCode);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public boolean updateUser(@RequestBody User user) throws Exception {
		return userService.updateUser(user);
	}

	/**
	 * 使用userCode和userPassword进行用户信息登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User doLoginUser(String userCode, String userPassword) throws Exception {
		return userService.doLoginUser(userCode, userPassword);
	}
}
