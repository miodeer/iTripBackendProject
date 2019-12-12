package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.Dto;
import cn.ekgc.itrip.transport.UserTransport;
import cn.ekgc.itrip.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户校验控制中心
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
@RestController("authController")
@RequestMapping("/auth")
public class AuthController extends BaseController {
	@Autowired
	private UserTransport userTransport;

	/**
	 * 对用户输入的手机/邮箱进行校验
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/ckusr", method = RequestMethod.GET)
	public Dto<User> checkUserCodeForRegistry(String name) throws Exception {
		Dto<User> result = new Dto<User>();
		if (UserUtil.checkUserCodePattern(name)) {
			User user = userTransport.getUserByUserCode(name);
			if (user == null) {
				result.setSuccess("true");
				return result;
			}
		}
		result.setSuccess("false");
		result.setMsg("您输入的手机号/邮箱有误或重复，请重新输入！");
		return result;
	}

	/**
	 * <b>使用邮箱注册新用户</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/doregister", method = RequestMethod.POST)
	public Dto<User> registryUserByEmail(@RequestBody User user) throws Exception {
		// 1、校验用户所填写的信息是否格式有效，包含用户的Email地址和密码

		// 2、对于用户的登录密码进行MD5加密
		// 3、保存用户数据，并且在业务层保存成功的是否使用邮箱功能发送激活码，并且将激活码设定到redis中保存，并且设定有效时间
		return null;
	}

}
