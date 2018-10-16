/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.api.impl;

import org.springframework.web.bind.annotation.RestController;

import pers.wl.site.api.TestServiceRpc;
import pers.wl.site.model.UserModel;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 上午9:47:58
 * @since JDK 1.8
 */
@RestController
public class TestServiceRpcImpl implements TestServiceRpc{

	/** 
	 * @see pers.wl.site.api.TestServiceRpc#sayHi()
	 */
	@Override
	public UserModel sayHi() {
		UserModel userModel = new UserModel();
		userModel.setName("小亮");
		userModel.setAge("18");
		return userModel;
	}

	/** 
	 * @see pers.wl.site.api.TestServiceRpc#sayHello()
	 */
	@Override
	public String sayHello() {
		return "hello";
	}
	
	
}
