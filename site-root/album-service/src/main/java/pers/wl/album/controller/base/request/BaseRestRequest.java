package pers.wl.album.controller.base.request;

import java.io.Serializable;

/**
 * rest接口请求参数基类
 * 
 * @author wuliang
 * @version $Id: BaseRestRequest.java, v 0.1 2018年11月6日 下午5:23:40 wuliang Exp $
 */
public abstract class BaseRestRequest implements Serializable {

	/** 序列号 */
	private static final long serialVersionUID = 3848884122968364361L;
	
	/**
	 * 请求参数逻辑校验
	 */
	public abstract void validateLogic();

}
