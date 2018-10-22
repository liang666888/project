/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import pers.wl.cache.redis.RedisUtils;
import pers.wl.site.model.album.GoodsInfoModel;

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
	public void putObjectCacheTest() {
		redisUtils.putObjectCache("test", "测试测试");
		redisUtils.putObjectCache("test1", "测试测试1");
		redisUtils.putObjectCache("test2", "测试测试2");
		GoodsInfoModel model = new GoodsInfoModel();
		model.setGoodsId(1);
		model.setGoodsName("测试什么鬼产品");
		model.setCreateTime(new Date());
		model.setGoodsSn("12133");
		model.setGoodsHeadImg("adaffsdf.jpg");
		model.setGoodsPrice(new BigDecimal("23"));
		model.setGoodsStatus("INIT");
		redisUtils.putObjectCache("test3", model);
		System.out.println(redisUtils.getObjectCache("test3").toString());
	}

	@Test
	public void getObjectCacheTest() {
		GoodsInfoModel model = redisUtils.getObjectCache("test3", GoodsInfoModel.class);
		Assert.assertNotNull(model);
		System.out.println(JSON.toJSONString(model));
	}

	@Test
	public void deleteKeysTest() {
		redisUtils.deleteKeys("test", "test1", "test2", "test3", "getAllGoodsInfo::pers.wl.album.service.impl.GoodsInfoServiceImplgetAll");
	}
	
	@Test
	public void existsKey() {
		Assert.assertTrue(
				redisUtils.existsKey("getAllGoodsInfo::pers.wl.album.service.impl.GoodsInfoServiceImplgetAll"));
	}
}
