package cn.ekgc.itrip.util;

/**
 * 用户工具类，用于校验等等
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
public class UserUtil {
	// Email地址正则表达式
	public static final String emailRegEx = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	// 手机号码正则表达式
	public static final String cellphoneRegEx = "^1[0-9]{10}$";

	/**
	 * 校验User的userCode格式是否正确
	 * @param userCode
	 * @return
	 */
	public static boolean checkUserCodePattern(String userCode) {
		if (userCode != null && !"".equals(userCode.trim())) {
			if (userCode.matches(emailRegEx)) {
				return true;
			} else if (userCode.matches(cellphoneRegEx)) {
				return true;
			}
		}
		return false;
	}
}
