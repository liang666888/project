package pers.wl.album.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import pers.wl.album.service.GoodsInfoService;
import pers.wl.site.api.album.GoodsInfoApi;

/**
 * 产品信息
 * @author liang.wu
 * @date 2018年10月21日 下午7:07:46
 * @since jdk1.7
 * @version v1.0
 *
 */
@RestController
public class GoodsInfoController implements GoodsInfoApi{
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	
}
