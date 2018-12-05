/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.controller.wechat.dto;

import java.io.Serializable;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月4日 下午3:50:18
 * @since JDK 1.8
 */
public class LoginTokenDto implements Serializable {

	/** */
	private static final long serialVersionUID = -8349552404427304452L;

	/**
	 * 登录token
	 */
	private String loginToken;
	
	public LoginTokenDto(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

}
