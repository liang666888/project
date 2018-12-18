/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import pers.wl.album.controller.wechat.dto.Code2SessionDto;
import pers.wl.album.controller.wechat.dto.LoginTokenDto;
import pers.wl.album.model.TbUserModel;
import pers.wl.album.util.AssertResultUtil;
import pers.wl.album.util.OkHttpUtil;
import pers.wl.album.util.wechat.WechatAppTokenUtil;
import pers.wl.cache.redis.RedisUtils;
import pers.wl.common.enums.exception.BizException;
import pers.wl.common.utils.result.ApiResult;
import pers.wl.common.utils.result.ApiResultUtil;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月4日 下午2:58:16
 * @since JDK 1.8
 */
@Service
public class LoginService {

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
	private String tokenExpire;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private UserService userService;

	/**
	 * 微信小程序登录
	 * 
	 * @param code
	 * @return
	 */
	public ApiResult<LoginTokenDto> wechatAppLogin(String code) {
		// 获取小程序用户openid、session_key
		String url = MessageFormat.format(code2SessionUrl, appid, secret, code);
		String resJson = OkHttpUtil.get(url, null);
		Code2SessionDto code2SessionDto = JSON.parseObject(resJson, Code2SessionDto.class);
		AssertResultUtil.equals(code2SessionDto.getErrcode() + "", "0",
				new BizException(code2SessionDto.getErrcode() + "", code2SessionDto.getErrmsg()));
		// 获取用户
		TbUserModel tbUserModel = this.getUser(code2SessionDto);
		// 缓存登录态
		String loginToken = this.cacheLogin(tbUserModel);
		// 返回loginToken
		return ApiResultUtil.success(new LoginTokenDto(loginToken));
	}

	/**
	 * 获取用户
	 * 
	 * @param code2SessionDto
	 * @return
	 */
	private TbUserModel getUser(Code2SessionDto code2SessionDto) {
		TbUserModel record = userService.findByOpenid(code2SessionDto.getOpenid());
		if (record == null) {
			// 新增用户
			TbUserModel tbUserModel = new TbUserModel();
			tbUserModel.setOpenid(code2SessionDto.getOpenid());
			tbUserModel.setUnionid(code2SessionDto.getUnionid());
			record = userService.add(tbUserModel);
		}
		return record;
	}

	/**
	 * 缓存登录态
	 * 
	 * @param tbUserModel
	 * @return
	 */
	private String cacheLogin(TbUserModel tbUserModel) {
		// 缓存openid、session_key
		String loginToken = WechatAppTokenUtil.generateLoginToken();
		redisUtils.putObjectCache(loginToken, tbUserModel, Long.valueOf(tokenExpire));
		return loginToken;
	}

	/**
	 * 校验loginToken有效性
	 * 
	 * @param loginToken
	 */
	public Code2SessionDto checkWechatAppLoginToken(String loginToken) {
		Code2SessionDto sessionDto = redisUtils.getObjectCache(loginToken, Code2SessionDto.class);
		return sessionDto;
	}
}
