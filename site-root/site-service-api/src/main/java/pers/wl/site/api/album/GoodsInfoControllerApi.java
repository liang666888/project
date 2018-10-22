package pers.wl.site.api.album;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pers.wl.site.model.album.GoodsInfoModel;

/**
 * 产品信息api
 * 
 * @author liang.wu
 * @date 2018年10月21日 下午7:08:50
 * @since jdk1.7
 * @version v1.0
 *
 */
@Api(tags = "产品信息api")
@RequestMapping("/goodsinfo-api")
public interface GoodsInfoControllerApi {

	@ApiOperation(value = "获取产品列表", notes = "说明：获取产品列表", response = GoodsInfoModel.class)
	@GetMapping("/getall")
	public List<GoodsInfoModel> getAll();
}
