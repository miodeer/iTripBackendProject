package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.Dto;
import cn.ekgc.itrip.transport.UserTransport;
import cn.ekgc.itrip.util.JWTUtil;
import cn.ekgc.itrip.util.MD5Util;
import cn.ekgc.itrip.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
		Dto<User> result = new Dto<User>();
		String userCode = user.getUserCode();
		String userPassword = user.getUserPassword();
		// 校验用户所填写的信息是否格式有效，包含用户的Email地址和密码
		if (userCode != null && !"".equals(userCode.trim())
				&& userPassword != null && !"".equals(userPassword.trim())) {
			// 校验格式
			if (UserUtil.checkUserCodePattern(userCode)) {
				// 校验唯一性
				if (userTransport.getUserByUserCode(userCode) == null) {
					// 2、对于用户的登录密码进行MD5加密
					// 3、保存用户数据，并且在业务层保存成功的是否使用邮箱功能发送激活码，并且将激活码设定到redis中保存，并且设定有效时间
					user.setUserPassword(MD5Util.encrypt(userPassword));
					if (userTransport.saveUser(user)) {
						result.setSuccess("true");
						result.setMsg("请在30分钟内登录您的邮箱，查看激活码");
					} else {
						result.setSuccess("false");
						result.setMsg("系统错误，请联系管理员:XXXXXXX");
					}
				} else {
					result.setSuccess("false");
					result.setMsg("该Email地址已被占用，请直接登录或激活");
				}
			} else {
				result.setSuccess("false");
				result.setMsg("请填写正确的Email地址");
			}
		} else {
			result.setSuccess("false");
			result.setMsg("请填写您的Email地址和登录密码");
		}
		return result;
	}

	/**
	 * 激活用户
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/activate", method = RequestMethod.PUT)
	public Dto<User> activateUser(@RequestParam String user, @RequestParam String code) throws Exception {
		Dto<User> result = new Dto<User>();
		User tempUser = userTransport.getUserByUserCode(user);
		if (tempUser != null) {
			if (code.equals(userTransport.getActiveCodeByUserCode(user))) {
				tempUser.setActivated(1);
				if (userTransport.updateUser(tempUser)) {
					result.setSuccess("true");
					result.setMsg("您已成功激活");
				} else {
					result.setSuccess("false");
					result.setMsg("系统错误，请联系管理员:XXXXXXX");
				}
			} else {
				result.setSuccess("false");
				result.setMsg("您的激活码有误");
			}
		} else {
			result.setSuccess("false");
			result.setMsg("该邮箱并未注册，请先注册");
		}
		return result;
	}

	/**
	 * 使用手机注册新用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/registerbyphone", method = RequestMethod.POST)
	public Dto<User> registryUserByPhone(@RequestBody User user) throws Exception {
		Dto<User> result = new Dto<User>();
		String userCode = user.getUserCode();
		String userPassword = user.getUserPassword();
		// 校验用户所填写的信息是否格式有效，包含用户的手机号和密码
		if (userCode != null && !"".equals(userCode.trim())
				&& userPassword != null && !"".equals(userPassword.trim())) {
			// 校验格式
			if (UserUtil.checkUserCodePattern(userCode)) {
				// 校验唯一性
				if (userTransport.getUserByUserCode(userCode) == null) {
					// 2、对于用户的登录密码进行MD5加密
					// 3、保存用户数据，并且在业务层保存成功的是否使用手机功能发送激活码，并且将激活码设定到redis中保存，并且设定有效时间
					user.setUserPassword(MD5Util.encrypt(userPassword));
					if (userTransport.saveUser(user)) {
						result.setSuccess("true");
						result.setMsg("请在30分钟内在您的手机上查看激活码");
					} else {
						result.setSuccess("false");
						result.setMsg("系统错误，请联系管理员:XXXXXXX");
					}
				} else {
					result.setSuccess("false");
					result.setMsg("该手机号已被占用，请直接登录或激活");
				}
			} else {
				result.setSuccess("false");
				result.setMsg("请填写正确的手机号");
			}
		} else {
			result.setSuccess("false");
			result.setMsg("请填写您的手机号和登录密码");
		}
		return result;
	}

	/**
	 * 根据用户的userCode和userPassword进行用户信息登录
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/dologin", method = RequestMethod.POST)
	public Dto<User> UserDoLogin(String name, String password) throws Exception {
		Dto<User> result = new Dto<User>();
		// 校验用户所填写的信息是否格式有效，包含用户的Email地址和密码
		if (name != null && !"".equals(name.trim())
				&& password != null && !"".equals(password.trim())) {
			password = MD5Util.encrypt(password);
			User user = userTransport.doLoginUser(name, password);
			if (user != null) {
				String json = JWTUtil.createToken(user.getId());
				response.setHeader("Authorization", json);

				result.setSuccess("true");
				result.setData(user);
				return result;
			}
		}
		result.setSuccess("false");
		result.setMsg("您的用户名或密码不正确，请检查后重新登陆");
		return result;
	}

}
