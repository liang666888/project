/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pers.wl.site.model.UserModel;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 上午10:55:56
 * @since JDK 1.8
 */
@Api(tags="测试api")
@RequestMapping("/test-api")
public interface TestControllerApi {
	
	@ApiOperation(value="获取用户",notes="描述:")
	@GetMapping("/getuser")
	public UserModel getUser();
	
	@ApiOperation(value="sayhello",notes="描述:")
	@GetMapping("/sayhello")
	public String sayHello();
}
