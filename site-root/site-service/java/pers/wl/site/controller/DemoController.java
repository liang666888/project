/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.api.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.wl.site.model.UserModel;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 下午1:16:11
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
	
	@GetMapping("/getuser")
	public UserModel getUser() {
		UserModel userModel = new UserModel();
		userModel.setName("小明");
		userModel.setAge("18");
		return userModel;
	}
}
