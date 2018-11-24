/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.controller.wechat.dto;

import java.io.Serializable;

/**
 * 描述说明 微信小程序登录态信息获取返回
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年11月24日 下午1:52:53
 * @since JDK 1.8
 */
public class Code2SessionDto implements Serializable{

	/** */
	private static final long serialVersionUID = -2150298882661440999L;
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
	/**
	 * 错误码
	 */
	private int errcode;
	/**
	 * 错误信息
	 */
	private String errmsg;

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

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
