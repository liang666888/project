/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

/** 
 * 描述说明
 * 初始化启动类
 * 注：@EnableEurekaClient springboot2.0以上可省略
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月12日 上午11:36:15
 * @since JDK 1.8
 */
@EnableSwagger2Doc
@SpringBootApplication
public class SiteServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiteServiceApplication.class, args);
	}
}
