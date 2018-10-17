/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.web.client;

import org.springframework.cloud.openfeign.FeignClient;

import pers.wl.site.api.TestControllerApi;

/** 
 * 描述说明
 * 当服务提供者设置了context-path,
 * fegin客户端需要配置path属性，否则调用不到服务（坑了）
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 上午11:28:36
 * @since JDK 1.8
 */
@FeignClient(value="site-service",path="site-service")
public interface TestControllerClient extends TestControllerApi{

}
