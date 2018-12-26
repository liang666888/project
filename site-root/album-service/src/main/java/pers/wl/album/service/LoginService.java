/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.service;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import pers.wl.album.common.annotation.ServiceOper;
import pers.wl.album.common.constants.AppConfigure;
import pers.wl.album.common.constants.BaseConstants;
import pers.wl.album.common.enums.ServiceOperEnum;
import pers.wl.album.controller.wechat.dto.Code2SessionDto;
import pers.wl.album.controller.wechat.dto.LoginTokenDto;
import pers.wl.album.controller.wechat.dto.LoginUser;
import pers.wl.album.model.TbUserModel;
import pers.wl.album.util.AssertResultUtil;
import pers.wl.album.util.OkHttpUtil;
import pers.wl.album.util.WebUtil;
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

	@Autowired
	private AppConfigure appConfigure;

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
	@ServiceOper(oper = ServiceOperEnum.WECHAT_APP_LOGIN)
	public ApiResult<LoginTokenDto> wechatAppLogin(String code) {
		// 先尝试从请求头中获取token
		String loginToken = WebUtil.getHttpServletRequest().getHeader(BaseConstants.WECHAT_APP_LOGIN_TOKEN);
		if (!(StringUtils.isNotBlank(loginToken) && redisUtils.existsKey(loginToken))) {
			// 获取小程序用户openid、session_key
			String url = MessageFormat.format(appConfigure.getCode2SessionUrl(), appConfigure.getAppid(),
					appConfigure.getSecret(), code);
			String resJson = OkHttpUtil.get(url, null);
			Code2SessionDto code2SessionDto = JSON.parseObject(resJson, Code2SessionDto.class);
			AssertResultUtil.equals(code2SessionDto.getErrcode() + "", "0",
					new BizException(code2SessionDto.getErrcode() + "", code2SessionDto.getErrmsg()));
			// 获取用户
			TbUserModel tbUserModel = this.getUser(code2SessionDto);
			// 缓存登录态
			loginToken = this.cacheLogin(tbUserModel, code2SessionDto.getSession_key());
		} else {

		}
		// 返回loginToken
		return ApiResultUtil.success(new LoginTokenDto(loginToken));
	}

	/**
	 * 获取用户
	 * 
	 * @param code2SessionDto
	 * @return 用户信息
	 */
	private TbUserModel getUser(Code2SessionDto code2SessionDto) {
		TbUserModel record = userService.findByOpenid(code2SessionDto.getOpenid());
		if (record == null) {
			// 用户不存在则新增用户
			TbUserModel tbUserModel = new TbUserModel();
			tbUserModel.setOpenid(code2SessionDto.getOpenid());
			tbUserModel.setUnionid(code2SessionDto.getUnionid());
			record = userService.add(tbUserModel);
		}
		return record;
	}

	/**
	 * 缓存登录用户信息
	 * 
	 * @param tbUserModel
	 * @param sessionKey
	 * @return
	 */
	private String cacheLogin(TbUserModel tbUserModel, String sessionKey) {
		String loginToken = WechatAppTokenUtil.generateLoginToken(tbUserModel.getOpenid());
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(tbUserModel.getUserId());
		loginUser.setOpenid(tbUserModel.getOpenid());
		loginUser.setUnionid(tbUserModel.getUnionid());
		loginUser.setSession_key(sessionKey);
		loginUser.setUsername(tbUserModel.getUsername());
		redisUtils.putObjectCache(loginToken, loginUser, Long.valueOf(appConfigure.getTokenExpireSecond()));
		return loginToken;
	}

	/**
	 * 校验loginToken有效性
	 * 
	 * @param loginToken
	 * @return
	 */
	public LoginUser checkWechatAppLoginToken(String loginToken) {
		LoginUser loginUser = redisUtils.getObjectCache(loginToken, LoginUser.class);
		return loginUser;
	}
}
