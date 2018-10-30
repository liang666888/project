/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月19日 下午5:37:31
 * @since JDK 1.8
 */
@EnableSwagger2Doc
@EntityScan("pers.wl.album.model")//@EntityScan("entity对应的包路径")
//@EnableJpaRepositories(basePackages={"pers.wl.album.repository"}) //@EnableJpaRepositories(basePackages={"dao层对应的包路径"})
@ComponentScan(basePackages = {"pers.wl.album","pers.wl.cache"})
@EnableRedisHttpSession	//开启redis集中式session管理，所有的session都存放到redis中
@SpringBootApplication
public class AlbumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumServiceApplication.class, args);
	}
}
