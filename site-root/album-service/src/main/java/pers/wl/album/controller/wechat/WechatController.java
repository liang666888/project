/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.controller.wechat;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pers.wl.album.controller.wechat.dto.Code2SessionDto;
import pers.wl.album.util.AssertResultUtil;
import pers.wl.album.util.OkHttpUtil;
import pers.wl.common.enums.exception.BizException;
import pers.wl.common.utils.result.ApiResult;
import pers.wl.common.utils.result.ApiResultUtil;


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

	@Value("${wechat.code2Session.url:}")
	private String code2SessionUrl;

	@Value("${wechat.appid}")
	private String appid;

	@Value("${wechat.secret}")
	private String secret;

	/**
	 * 微信小程序登录
	 * 
	 * @param code
	 * @return
	 */
	@ApiOperation(value = "微信小程序登录", notes = "说明：微信小程序登录")
	@GetMapping("/login/{code}")
	public ApiResult<Code2SessionDto> login(@PathVariable("code") String code) {
		String url = MessageFormat.format(code2SessionUrl, appid, secret, code);
		String resJson = OkHttpUtil.get(url, null);
		Code2SessionDto code2SessionDto = JSON.parseObject(resJson, Code2SessionDto.class);
		AssertResultUtil.equals(code2SessionDto.getErrcode() + "", "0",
				new BizException(code2SessionDto.getErrcode() + "", code2SessionDto.getErrmsg()));
		return ApiResultUtil.success(code2SessionDto);
	}
}
