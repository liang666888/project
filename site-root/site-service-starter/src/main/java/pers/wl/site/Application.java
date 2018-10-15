/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/** 
 * 描述说明
 * 初始化启动类
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月12日 上午11:36:15
 * @since JDK 1.8
 */
@EnableEurekaClient
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
