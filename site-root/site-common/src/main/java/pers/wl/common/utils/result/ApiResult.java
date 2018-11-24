package pers.wl.common.utils.result;

import java.io.Serializable;

/**
 * rest接口统一返回对象类
 * 
 * @author wuliang
 * @version $Id: ResultDto.java, v 0.1 2018年11月4日 下午4:21:46 wuliang Exp $
 */
public class ApiResult<T extends Object> implements Serializable {

	/** 序列号 */
	private static final long serialVersionUID = 5309825731677338650L;

	/** 返回码 */
	private String code;

	/** 返回信息 */
	private String msg;
	
	/** 分页查询时有值，总条数 */
	private Integer count;

	/** 返回数据 */
	private T data;
	
	public ApiResult() {}

	public ApiResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ApiResult(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
