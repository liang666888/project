package pers.wl.cache.redis;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 自定义redis key序列化类,以服务名作为key前缀
 * 
 * @author liang.wu
 * @date 2018年10月21日 下午10:48:30
 * @since jdk1.7
 * @version v1.0
 *
 */
@Component
public class MyRedisKeySerializer implements RedisSerializer<String> {

	private final static Logger logger = LoggerFactory.getLogger(MyRedisKeySerializer.class);

	@Value("${spring.application.name:site}")
	private String keyPrefix;

	private final Charset charset;

	private final String KEY_SPLIT_CASE = ":"; //keyPrefix+KEY_SPLIT_CASE+key

	public MyRedisKeySerializer() {
		this(Charset.forName("UTF8"));
	}

	public MyRedisKeySerializer(Charset charset) {
		Assert.notNull(charset, "Charset must not be null!");
		this.charset = charset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.serializer.RedisSerializer#serialize(java.
	 * lang.Object)
	 */
	@Override
	public byte[] serialize(String t) throws SerializationException {
		String key = keyPrefix + KEY_SPLIT_CASE + t;
		logger.info("key:{},getBytes:{}", key, key.getBytes(charset));
		return (key == null ? null : key.getBytes(charset));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.serializer.RedisSerializer#deserialize(
	 * byte[])
	 */
	@Override
	public String deserialize(byte[] bytes) throws SerializationException {
		String saveKey = new String(bytes, charset);
		int indexOf = saveKey.indexOf(keyPrefix+KEY_SPLIT_CASE);
		if (indexOf > 0) {
			logger.warn("key缺少前缀");
		} else {
			saveKey = saveKey.substring(indexOf);
		}
		logger.info("saveKey:{}", saveKey);
		return (saveKey.getBytes() == null ? null : saveKey);
	}

}
