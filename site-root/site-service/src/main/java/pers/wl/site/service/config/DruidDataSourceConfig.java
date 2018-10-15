/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.service.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;

/** 
 * 描述说明
 * druid数据源配置
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月12日 下午2:32:55
 * @since JDK 1.8
 */
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidDataSourceConfig {
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private int initialSize;
	private int minIdle;
	private int maxActive;
	private int maxWait;
	private int timeBetweenEvictionRunsMillis;
	private int minEvictableIdleTimeMillis;
	private String validationQuery;
	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;
	private String filters;
	private String connectionProperties;
 
	/**
	 * 
	 * 方法描述
	 * 解决 spring.datasource.filters=stat,wall,log4j 无法正常注册
	 * 在同样的DataSource中，首先使用被标注的DataSource
	 * @return
	 */
	@Bean 
	@Primary
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		
		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		try {
			datasource.setFilters(filters);
		}catch (Exception e) {
			System.err.println("druid configuration initialization filter: " + e);
		}
		datasource.setConnectionProperties(connectionProperties);
		return datasource;
	}
 
	// 此处省略属性的get和set方法
	
}

