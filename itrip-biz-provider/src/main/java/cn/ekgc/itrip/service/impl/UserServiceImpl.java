package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.UserDao;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.service.UserService;
import cn.ekgc.itrip.util.ConstantUtil;
import cn.ekgc.itrip.util.SystemCodeUtil;
import cn.ekgc.itrip.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务层实现类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * <b>通过userCode查询User对象</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getUserByUserCode(String userCode) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);
		List<User> userList = userDao.findUserByQuery(queryMap);
		if (userList.size()>0 & userList!=null) {
			return userList.get(0);
		}
		return null;
	}

	/**
	 * 存储用户信息user
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveUser(User user) throws Exception {
		try {
			userDao.saveUser(user);
			String activeCode = SystemCodeUtil.createActiveCode();
			redisTemplate.opsForValue().set(user.getUserCode(), activeCode);
			redisTemplate.expire(user.getUserCode(), ConstantUtil.ACTIVE_CODE_TIMEOUT*60, TimeUnit.SECONDS);

			// 用户使用邮箱注册
			if (user.getUserCode().matches(UserUtil.emailRegEx)) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setFrom(ConstantUtil.MAIL_FROM);
				mailMessage.setTo(user.getUserCode());
				mailMessage.setSubject("爱旅行注册激活码");
				mailMessage.setText("您的激活码是："+ activeCode + "，请在" + ConstantUtil.ACTIVE_CODE_TIMEOUT + "分钟内登录系统，输入本验证码激活您的账户！");
				mailSender.send(mailMessage);
				return true;
			} else if (user.getUserCode().matches(UserUtil.cellphoneRegEx)) { // 用户使用手机号注册
			
			
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 激活用户
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public String getActiveCodeByUserCode(String userCode) throws Exception {
		String activeCode = new String();
		try {
			activeCode = redisTemplate.opsForValue().get(userCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeCode;
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateUser(User user) throws Exception {
		if (userDao.updateUser(user)) {
			return true;
		}
		return false;
	}

	/**
	 * 使用userCode和userPassword进行用户信息登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	public User doLoginUser(String userCode, String userPassword) throws Exception {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("userCode", userCode);
		queryMap.put("userPassword", userPassword);
		queryMap.put("activated", 1);
		List<User> userList = userDao.findUserByQuery(queryMap);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
}
