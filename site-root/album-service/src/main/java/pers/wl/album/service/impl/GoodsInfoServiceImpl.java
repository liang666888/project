package pers.wl.album.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import pers.wl.album.repository.GoodsInfoRepository;
import pers.wl.album.service.GoodsInfoService;
import pers.wl.site.model.album.GoodsInfoModel;

/**
 * 
 * 描述说明 缓存注解使用说明 1.@CacheConfig(cacheNames="users") 注解指的是该类中的缓存的名称都是users
 * 2.@CachePut(key="'userCache'")中userCache要加''单引号，表示这是一个字符串。
 * 3.@Cacheable能够根据方法的请求参数对其结果进行缓存(缓存的是方法的返回结果)，一般用于查询操作
 * 4.@CachePut(key="'userCache'")主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable
 * 不同的是，它每次都会触发真实方法的调用，一般用于update()，insert()操作
 * 5.@CacheEvict(key="'userCache'")主要针对方法配置，能够根据一定的条件对缓存进行清空，一般用于delete()操作
 * 
 * 本例中的@Cacheable和@CachePut和@CacheEvict的key值必须都是同一个缓存的key，因为这样当update的时候缓存的时候，
 * get方法的得到的才是最新数据，而当删除的时候@CacheEvict，也必须把该key的缓存删除。
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月22日 下午2:49:12
 * @since JDK 1.8
 */
@CacheConfig(cacheNames = "GoodsInfoServiceImpl")
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

	private final static Logger logger = LoggerFactory.getLogger(GoodsInfoServiceImpl.class);

	@Autowired
	private GoodsInfoRepository goodsInfoRepository;

	@CachePut(key = "#p0.goodsId")
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public GoodsInfoModel add(GoodsInfoModel goodsInfoModel) {
		logger.info("数据插入goodsInfoRepository.save(goodsInfoModel)：{}",JSON.toJSONString(goodsInfoModel));
		return goodsInfoRepository.save(goodsInfoModel);
	}

	@CacheEvict(key = "#p0")
	@Modifying
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public void delete(Integer id) {
		logger.info("数据删除goodsInfoRepository.deleteById(id)：{}",id);
		goodsInfoRepository.deleteById(id);
	}

	@CachePut(key = "#p0.goodsId")
	@Modifying
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public GoodsInfoModel update(GoodsInfoModel goodsInfoModel) {
		logger.info("数据更新goodsInfoRepository.saveAndFlush(goodsInfoModel)：{}",JSON.toJSONString(goodsInfoModel));
		return goodsInfoRepository.saveAndFlush(goodsInfoModel);
	}

	@Cacheable(key = "#p0")
	public GoodsInfoModel get(Integer id) {
		logger.info("数据查询goodsInfoRepository.getOne(id)：{}",id);
		return goodsInfoRepository.getOne(id);
	}

	@Cacheable(value = "getAllGoodsInfo", keyGenerator = "wiselyKeyGenerator")
	public List<GoodsInfoModel> getAll() {
		return goodsInfoRepository.findAll();
	}

	public Page<GoodsInfoModel> getPage(Integer page, Integer rows) {
		Pageable pageable = new PageRequest(page - 1, rows);
		return goodsInfoRepository.findAll(pageable);
	}
}