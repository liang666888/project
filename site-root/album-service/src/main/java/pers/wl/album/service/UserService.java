/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.wl.album.common.annotation.ServiceOper;
import pers.wl.album.common.enums.ServiceOperEnum;
import pers.wl.album.model.TbUserModel;
import pers.wl.album.repository.TbUserRepository;

/**
 * 描述说明 用户管理
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月14日 上午10:27:58
 * @since JDK 1.8
 */
@Service
public class UserService {

	@Autowired
	private TbUserRepository tbUserRepository;

	/**
	 * 新增用户
	 * 
	 * @param tbUserModel
	 * @return
	 */
	@ServiceOper(oper = ServiceOperEnum.ADD_USER)
	public TbUserModel add(TbUserModel tbUserModel) {
		return tbUserRepository.save(tbUserModel);
	}

	/**
	 * 根据openid查找用户
	 * 
	 * @param openid
	 * @return
	 */
	@ServiceOper(oper = ServiceOperEnum.FIND_USER_BY_OPENID)
	public TbUserModel findByOpenid(String openid) {
		return tbUserRepository.findByOpenid(openid);
	}

}
