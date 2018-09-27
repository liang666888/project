/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package com.cloud.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年7月27日 下午2:18:29
 * @since JDK 1.8
 */
@RefreshScope
@RestController
public class TestController {
	
	@Value("${content}")
	private String content;
	/**
	 * 
	 * 方法描述
	 *
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "hello demo:"+content;
	}
}
