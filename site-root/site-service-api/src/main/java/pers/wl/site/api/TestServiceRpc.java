/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.wl.site.model.UserModel;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 上午10:55:56
 * @since JDK 1.8
 */
@RequestMapping("test-service-rpc")
public interface TestServiceRpc {
	
	@GetMapping("/sayhi")
	public UserModel sayHi();
	
	@GetMapping("/sayHello")
	public String sayHello();
}
