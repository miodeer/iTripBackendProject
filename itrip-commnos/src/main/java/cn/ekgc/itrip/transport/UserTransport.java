package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户传输层
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/user")
public interface UserTransport {

	/**
	 * 通过userCode查询User对象
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/code", method = RequestMethod.POST)
	User getUserByUserCode(@RequestParam String userCode) throws Exception;

	/**
	 * 存储用户信息user
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	boolean saveUser(@RequestBody User user) throws Exception;

	/**
	 * 激活用户
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activate", method = RequestMethod.POST)
	String getActiveCodeByUserCode(@RequestParam String userCode) throws Exception;

	/**
	 * 修改用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	boolean updateUser(@RequestBody User user) throws Exception;

	/**
	 * 使用userCode和userPassword进行用户信息登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	User doLoginUser(@RequestParam String userCode, @RequestParam String userPassword) throws Exception;
}
