/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.wl.site.model.UserModel;
import pers.wl.site.web.client.DemoClient;
import pers.wl.site.web.client.TestServiceClient;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 上午11:17:50
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestServiceClient testServiceClient;
	
	@Autowired
	DemoClient demoClient;

	@GetMapping("/sayhi")
	public UserModel sayHi() {
		UserModel userModel = demoClient.getUser();
		System.out.println(userModel.getName());
		System.out.println(testServiceClient.sayHello());
		return userModel;
	}
}
