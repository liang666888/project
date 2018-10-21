package pers.wl.album.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.wl.album.repository.GoodsInfoRepository;
import pers.wl.album.service.GoodsInfoService;
import pers.wl.site.model.album.GoodsInfoModel;

@CacheConfig(cacheNames="goodsInfoService")
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
	@Autowired
	private GoodsInfoRepository goodsInfoRepository;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public GoodsInfoModel add(GoodsInfoModel goodsInfoModel) {
		return goodsInfoRepository.save(goodsInfoModel);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public void delete(Integer id) {
		goodsInfoRepository.deleteById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public GoodsInfoModel update(GoodsInfoModel goodsInfoModel) {
		return goodsInfoRepository.saveAndFlush(goodsInfoModel);
	}

	public GoodsInfoModel get(Integer id) {
		return goodsInfoRepository.getOne(id);
	}
	
	@Cacheable(value = "getAllGoodsInfo",keyGenerator="wiselyKeyGenerator") 
	public List<GoodsInfoModel> getAll() {
		return goodsInfoRepository.findAll();
	}

	public Page<GoodsInfoModel> getPage(Integer page, Integer rows) {
		Pageable pageable = new PageRequest(page - 1, rows);
		return goodsInfoRepository.findAll(pageable);
	}
}