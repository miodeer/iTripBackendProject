package cn.ekgc.itrip.util;

import java.util.Properties;

/**
 * 系统常量工具类
 * @author wang
 * @version 3.1.1
 * @since 2019-12-13
 */
public class ConstantUtil {
	private static final Properties PROP = new Properties();

	static {
		try {
			PROP.load(ConstantUtil.class.getClassLoader().getResourceAsStream("itrip.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final Long ACTIVE_CODE_TIMEOUT = Long.parseLong(PROP.getProperty("active.code.timeout"));

	public static final String MAIL_FROM = PROP.getProperty("mail.from");

	public static final String AUTH_SECRET = PROP.getProperty("auth.secret");

}
