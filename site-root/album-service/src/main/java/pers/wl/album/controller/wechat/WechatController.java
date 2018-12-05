/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pers.wl.album.controller.wechat.dto.LoginTokenDto;
import pers.wl.album.service.LoginService;
import pers.wl.common.utils.result.ApiResult;


/**
 * 描述说明 微信服务接口
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年11月24日 上午11:44:16
 * @since JDK 1.8
 */
@Api(tags="微信相关接口")
@RestController
@RequestMapping("/wechat")
public class WechatController {
	
	@Autowired
	private LoginService loginService;

	/**
	 * 微信小程序登录
	 * 
	 * @param code
	 * @return
	 */
	@ApiOperation(value = "微信小程序登录", notes = "说明：微信小程序登录")
	@GetMapping("/login/{code}")
	public ApiResult<LoginTokenDto> login(@PathVariable("code") String code) {
		return loginService.wechatAppLogin(code);
	}
}
