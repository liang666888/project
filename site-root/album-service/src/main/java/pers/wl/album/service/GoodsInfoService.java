package pers.wl.album.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pers.wl.album.model.GoodsInfoModel;

public interface GoodsInfoService {
	public GoodsInfoModel add(GoodsInfoModel goodsInfoModel);

	public void delete(Integer id);

	public GoodsInfoModel update(GoodsInfoModel goodsInfoModel);

	public GoodsInfoModel get(Integer id);

	public List<GoodsInfoModel> getAll();

	public Page<GoodsInfoModel> getPage(Integer page, Integer rows);
}