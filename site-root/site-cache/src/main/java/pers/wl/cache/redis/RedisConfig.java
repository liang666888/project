/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.cache.redis;

import java.lang.reflect.Method;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 描述说明 redis配置类，主要覆盖默认序列化方式 使用@EnableCaching开启声明式缓存支持，这样就可以使用基于注解的缓存技术
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月15日 上午11:24:52
 * @since JDK 1.8
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	private final static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Autowired
	private MyRedisKeySerializer myRedisKeySerializer;
	
	/**
	 * 缓存对象白名单，只有在该指定包下的类能使用
	 * 自定义序列化方式进行正常序列化与反序列化
	 */
	@Value("${redis.accept.white-list:}")
	private String redisAcceptList;
	
	/**
	 * 默认不过期
	 */
	private Duration timeToLive = Duration.ZERO;

	public void setTimeToLive(Duration timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * 缓存对象集合中，缓存是以 key-value 形式保存的。 当不指定缓存的 key 时，SpringBoot 会使用 SimpleKeyGenerator
	 * 生成 key。 自定义key. 这个可以不用
	 * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
	 * 
	 * @author liang.wu
	 * @date 2018年10月21日 下午8:23:01
	 * @version v1.0
	 * @return
	 *
	 */
	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};

	}

	/**
	 * 自定义RedisCacheManager加载
	 * 
	 * @author liang.wu
	 * @date 2018年10月21日 下午10:12:15
	 * @version v1.0
	 * @param connectionFactory
	 * @return
	 *
	 */
	@Bean
	@Primary // 当有多个管理器的时候，必须使用该注解在一个管理器上注释：表示该管理器为默认的管理器
	@SuppressWarnings("all")
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		// fastjson序列化
		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(this.timeToLive)
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(myRedisKeySerializer))
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
				.disableCachingNullValues();

		RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(config)
				.transactionAware().build();
		/*
		 * 设置白名单-非常重要
		 * 使用fastjson的时候：序列化时将class信息写入，反解析的时候， 
		 * fastjson默认情况下会开启autoType的检查，相当于一个白名单检查，
		 * 经实践测试，如果序列化信息中的类路径不在autoType中， 反解析就会报如下错误：
		 * com.alibaba.fastjson.JSONException: autoType is not support. pers.wl.site.model.album.GoodsInfoModel
		 */
		ParserConfig.getGlobalInstance().addAccept(redisAcceptList);
		logger.info("自定义RedisCacheManager加载完成");
		return redisCacheManager;
	}

	/**
	 * 方法描述 覆盖默认配置 RedisTemplate，使用 String 类型作为key， 设置key/value的序列化规则
	 * 
	 * @author liang.wu
	 * @date 2018年10月21日 下午8:23:24
	 * @version v1.0
	 * @param redisConnectionFactory
	 * @return
	 *
	 */
	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		// fastjson序列化
		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		// value值的序列化采用fastJsonRedisSerializer
		redisTemplate.setValueSerializer(fastJsonRedisSerializer);
		redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
		// key的序列化采用MyRedisKeySerializer
		redisTemplate.setKeySerializer(myRedisKeySerializer);
		redisTemplate.setHashKeySerializer(myRedisKeySerializer);
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.afterPropertiesSet();
		logger.info("自定义RedisTemplate加载完成");
		return redisTemplate;
	}

	@Bean
	public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	@Bean
	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}

}
