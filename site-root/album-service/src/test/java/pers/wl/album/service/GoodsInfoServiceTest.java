package pers.wl.album.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import pers.wl.site.model.album.GoodsInfoModel;

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
	
	@Test
	public void getAllTest(){
		List<GoodsInfoModel> list = goodsInfoService.getAll();
		Assert.assertNotNull(list);
		System.out.println(JSON.toJSONString(list));
	}
	
	
}
