package pers.wl.album.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import pers.wl.album.service.GoodsInfoService;
import pers.wl.site.api.album.GoodsInfoControllerApi;
import pers.wl.site.model.album.GoodsInfoModel;

/**
 * 产品信息
 * @author liang.wu
 * @date 2018年10月21日 下午7:07:46
 * @since jdk1.7
 * @version v1.0
 *
 */
@RestController
public class GoodsInfoController implements GoodsInfoControllerApi{
	
	@Autowired
	private GoodsInfoService goodsInfoService;

	/** 
	 * @see pers.wl.site.api.album.GoodsInfoControllerApi#getAll()
	 */
	@Override
	public List<GoodsInfoModel> getAll() {
		return goodsInfoService.getAll();
	}
	
	
}
