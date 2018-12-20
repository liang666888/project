package pers.wl.album.util;

import pers.wl.album.controller.wechat.dto.LoginUser;

/**
 * 用户信息上下文（当前操作线程下）
 * 
 * @author wuliang
 * @version $Id: UserInfoThreadUtil.java, v 0.1 2018年11月14日 下午2:49:39 wuliang
 *          Exp $
 */
public class UserContextUtil {

	private static final ThreadLocal<LoginUser> userContext = new ThreadLocal<LoginUser>();

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 */
	public static LoginUser getUserContext() {
		return userContext.get();
	}

	/**
	 * 设置当前登录用户上下文
	 * 
	 * @param userInfoDto
	 */
	public static void setUserContext(LoginUser loginUser) {
		userContext.set(loginUser);
	}

	/**
	 * 清除当前登录用户上下文
	 */
	public static void removeUserContext() {
		userContext.remove();
	}

	/**
	 * 获取用户ID
	 * 
	 * @return
	 */
	public static Integer getUserId() {
		LoginUser loginUser = userContext.get();
		if (loginUser != null) {
			return loginUser.getUserId();
		}
		return null;
	}
}
