/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月20日 下午2:55:09
 * @since JDK 1.8
 */
@Configuration
public class AppConfigure {
	/**
	 * 微信小程序用户登录态信息获取URL
	 */
	@Value("${wechat.code2Session.url:}")
	private String code2SessionUrl;

	/**
	 * 小程序appid
	 */
	@Value("${wechat.appid}")
	private String appid;

	/**
	 * 小程序secret
	 */
	@Value("${wechat.secret}")
	private String secret;

	/**
	 * loginToken过期时间
	 */
	@Value("${wechat.token.expire:1200}")
	private String tokenExpireSecond;

	/**
	 * 登录不拦截uri
	 */
	@Value("${login.except.uri}")
	private String loginExceptUri;

	public String getCode2SessionUrl() {
		return code2SessionUrl;
	}

	public void setCode2SessionUrl(String code2SessionUrl) {
		this.code2SessionUrl = code2SessionUrl;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getTokenExpireSecond() {
		return tokenExpireSecond;
	}

	public void setTokenExpireSecond(String tokenExpireSecond) {
		this.tokenExpireSecond = tokenExpireSecond;
	}

	public String getLoginExceptUri() {
		return loginExceptUri;
	}

	public void setLoginExceptUri(String loginExceptUri) {
		this.loginExceptUri = loginExceptUri;
	}

}
