/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import pers.wl.site.model.UserModel;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 下午1:18:31
 * @since JDK 1.8
 */
@FeignClient(value="site-service",path="site-service")
public interface DemoClient {
	
	@GetMapping("/demo/getuser")
	UserModel getUser();
}
