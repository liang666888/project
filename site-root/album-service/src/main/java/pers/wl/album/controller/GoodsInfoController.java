package pers.wl.album.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pers.wl.album.model.GoodsInfoModel;
import pers.wl.album.service.GoodsInfoService;

/**
 * 产品信息
 * @author liang.wu
 * @date 2018年10月21日 下午7:07:46
 * @since jdk1.7
 * @version v1.0
 *
 */
@Api(tags = "商品信息api")
@RequestMapping("/goodsinfo-api")
@RestController
public class GoodsInfoController{
	
	@Autowired
	private GoodsInfoService goodsInfoService;

	@ApiOperation(value = "获取商品列表", notes = "说明：获取商品列表")
	@GetMapping("/getall")
	public List<GoodsInfoModel> getAll() {
		return goodsInfoService.getAll();
	}
	
	@ApiOperation(value = "获取商品信息", notes = "说明：根据商品ID获取商品")
	@GetMapping("/get/{goodsId}")
	public GoodsInfoModel get(@PathVariable(name="goodsId")Integer goodsId) {
		return goodsInfoService.get(goodsId);
	}
	
	
}
