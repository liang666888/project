package pers.wl.album.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import pers.wl.album.model.GoodsInfoModel;
import pers.wl.cache.redis.RedisUtils;

/**
 * 
 * @author liang.wu
 * @date 2018年10月21日 下午8:29:40
 * @since jdk1.7
 * @version v1.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GoodsInfoServiceTest {
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllTest(){
		List<GoodsInfoModel> list = goodsInfoService.getAll();
		Assert.assertNotNull(list);
		List<GoodsInfoModel> list2 = (List<GoodsInfoModel>)redisUtils.getObjectCache("getAllGoodsInfo");
		System.out.println(JSON.toJSONString(list2));
	}
	
	@Test
	public void addTest() {
		GoodsInfoModel model = new GoodsInfoModel();
		model.setGoodsName("再来一个2");
		model.setCreateTime(new Date());
		model.setGoodsSn("123456");
		model.setGoodsHeadImg("test.jpg");
		model.setGoodsPrice(new BigDecimal("28"));
		model.setGoodsStatus("INIT");
		GoodsInfoModel saveModel =  goodsInfoService.add(model);
		Assert.assertNotNull(saveModel);
		System.out.println(JSON.toJSONString(saveModel));
	}
	
	@Test
	public void getTest() {
		GoodsInfoModel model = goodsInfoService.get(6);
		Assert.assertNotNull(model);
		System.out.println(JSON.toJSONString(model));
	}
	
	@Test
	public void updateTest() {
		GoodsInfoModel model = goodsInfoService.get(6);
		model.setGoodsName("修改名称-555");
		GoodsInfoModel updateModel = goodsInfoService.update(model);
		Assert.assertNotNull(updateModel);
	}
	
	@Test
	public void deleteTest() {
		goodsInfoService.delete(4);
	}
}
