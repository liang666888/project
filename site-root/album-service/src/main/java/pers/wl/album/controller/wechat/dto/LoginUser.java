/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.controller.wechat.dto;

import java.io.Serializable;

/**
 * 描述说明 登录用户
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月14日 下午12:13:56
 * @since JDK 1.8
 */
public class LoginUser implements Serializable {

	/** */
	private static final long serialVersionUID = 5832745846089699608L;

	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 用户唯一标识
	 */
	private String openid;
	/**
	 * 会话密钥
	 */
	private String session_key;
	/**
	 * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
	 */
	private String unionid;

	private String username;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
