package cn.ekgc.itrip.util;

import java.util.Random;

/**
 * 校验码生成
 * @author wang
 * @version 3.1.0
 * @since 2019-12-13
 */
public class SystemCodeUtil {
	/**
	 * 随机生成激活用的四位校验码
	 * @return
	 * @throws Exception
	 */
	public static String createActiveCode() throws Exception {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<4; i++) {
			int code = random.nextInt(10);
			sb.append(code);
		}
		return sb.toString();
	}
}
