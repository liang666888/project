/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.util.wechat;

import java.util.UUID;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月4日 下午2:35:04
 * @since JDK 1.8
 */
public class WechatAppTokenUtil {

	/**
	 * 生成小程序登录token(作为登录信息redis缓存key)
	 * openid+UUID
	 * @param openid
	 * @return loginToken
	 */
	public static String generateLoginToken(String openid) {
		return openid+UUID.randomUUID().toString();
	}
}
