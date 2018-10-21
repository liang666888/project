/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.cache.redis;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 描述说明 redis操作工具类
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月15日 上午11:39:00
 * @since JDK 1.8
 */
@Component
public class RedisUtils {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ValueOperations<String, Object> valueOperator;
	@Autowired
	private HashOperations<String, String, Object> hashOperator;
	@Autowired
	private ListOperations<String, Object> listOperator;
	@Autowired
	private SetOperations<String, Object> setOperator;
	@Autowired
	private ZSetOperations<String, Object> zSetOperator;

	/**
	 * 默认过期时长，单位：秒
	 */
	public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

	/** 不设置过期时长 */
	public final static long NOT_EXPIRE = -1;

	/**
	 * Redis的根操作路径,以服务名作为key前缀
	 */
	@Value("${spring.application.name:site}")
	private String category;

	public RedisUtils setCategory(String category) {
		this.category = category;
		return this;
	}

	/**
	 * 
	 * 获取Key的全路径
	 * 
	 * @param key
	 * @return
	 */
	public String getFullKey(String key) {
		return this.category + ":" + key;
	}

	/**
	 * 
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean existsKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 
	 * 判断key存储的值类型
	 * 
	 * @param key
	 * @return
	 */
	public DataType typeKey(String key) {
		return redisTemplate.type(key);
	}

	/**
	 * 
	 * 重命名key. 如果newKey已经存在，则newKey的原值被覆盖
	 * 
	 * @param oldKey
	 * @param newKey
	 */
	public void renameKey(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * 
	 * newKey不存在时才重命名
	 * 
	 * @param oldKey
	 * @param newKey
	 * @return
	 */
	public boolean renameKeyNx(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey,newKey);
	}

	/**
	 * 
	 * 删除key
	 * 
	 * @param key
	 */
	public void deleteKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 
	 * 删除key(可传入多个key)
	 * 
	 * @param keys
	 */
	public void deleteKeys(String... keys) {
		Set<String> ks = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(ks);
	}

	/**
	 * 
	 * 删除key（key集合）
	 * 
	 * @param keys
	 */
	public void deleteKeys(Collection<String> keys) {
		Set<String> ks = keys.stream().map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(ks);
	}

	/**
	 * 
	 * 设置key的生命周期
	 * 
	 * @param key
	 * @param time
	 *            时间数
	 * @param timeUnit
	 *            时间单位
	 */
	public void expireKey(String key, long time, TimeUnit timeUnit) {
		redisTemplate.expire(key, time, timeUnit);
	}

	/**
	 * 
	 * 设置key在指定的日期过期
	 * 
	 * @param key
	 * @param date
	 *            指定日期
	 */
	public void expireKeyAt(String key, Date date) {
		redisTemplate.expireAt(key, date);
	}

	/**
	 * 
	 * 查询key的生命周期
	 * 
	 * @param key
	 * @param timeUnit
	 *            时间单位
	 * @return 指定时间单位的时间数
	 */
	public long getKeyExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}

	/**
	 * 
	 * 将key设置为永久有效
	 * 
	 * @param key
	 */
	public void persistKey(String key) {
		redisTemplate.persist(key);
	}

	/**
	 * 获取Object结果缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object getObjectCache(String key) {
		Object value = valueOperator.get(key);
		return value;
	}

	/**
	 * 获取对象缓存
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getObjectCache(String key, Class<T> clazz) {
		Object value = valueOperator.get(key);
		return JSON.parseObject(JSON.toJSONString(value), clazz);
	}
	
	/**
	 * 存入Object
	 * 
	 * @param key
	 * @param obj
	 */
	public void putObjectCache(String key,Object obj) {
		valueOperator.set(key, obj);
	}
	
	/**
	 * 存入Object并设置过期时间
	 * 
	 * @param key
	 * @param obj
	 * @param expireSecondTime 过期时间（单位：秒）
	 */
	public void putObjectCache(String key, Object obj, long expireSecondTime) {
		valueOperator.set(key, obj, expireSecondTime, TimeUnit.SECONDS);
	}
}
