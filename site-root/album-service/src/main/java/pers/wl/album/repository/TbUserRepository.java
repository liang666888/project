/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.wl.album.model.TbUserModel;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月14日 上午10:25:30
 * @since JDK 1.8
 */
public interface TbUserRepository extends JpaRepository<TbUserModel, Integer>{
	
	TbUserModel findByOpenid(String openid);
}
