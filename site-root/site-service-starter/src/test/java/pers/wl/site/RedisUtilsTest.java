/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.wl.site.cache.redis.RedisUtils;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月15日 下午2:50:46
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisUtilsTest {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Test
	public void putObjectTest() {
		redisUtils.putObjectCache("test", "测试测试");
		redisUtils.putObjectCache("test1", "测试测试1");
		redisUtils.putObjectCache("test2", "测试测试2");
		System.out.println(redisUtils.getObjectCache("test").toString());
	}
	
	@Test
	public void deleteKeysTest() {
		redisUtils.deleteKeys("test","test1","test2");
	}
}
