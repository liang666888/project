package pers.wl.common.utils.result;

import pers.wl.common.enums.RetCodeEnum;

/**
 * rest接口统一返回工具类
 * 
 * @author wuliang
 * @version $Id: ResultUtil.java, v 0.1 2018年11月4日 下午4:16:16 wuliang Exp $
 */
public class ApiResultUtil {

	/**
	 * 成功返回
	 * 
	 * @return
	 */
	public static <T> ApiResult<T> success() {
		return new ApiResult<T>(RetCodeEnum.SUCCESS.getCode(), RetCodeEnum.SUCCESS.getDesc());
	}

	/**
	 * 成功返回
	 * 
	 * @param data
	 *            返回数据
	 * @return
	 */
	public static <T> ApiResult<T> success(T data) {
		return new ApiResult<T>(RetCodeEnum.SUCCESS.getCode(), RetCodeEnum.SUCCESS.getDesc(), data);
	}

	/**
	 * 成功返回(分页)
	 * 
	 * @param data
	 *            返回数据
	 * @param count
	 *            总条数
	 * @return
	 */
	public static <T> ApiResult<T> success(T data, Integer count) {
		ApiResult<T> apiResult = new ApiResult<T>(RetCodeEnum.SUCCESS.getCode(), RetCodeEnum.SUCCESS.getDesc(), data);
		apiResult.setCount(count);
		return apiResult;
	}

	/**
	 * 错误返回
	 * 
	 * @param errorCodeEnum
	 *            错误枚举
	 * @return
	 */
	public static <T> ApiResult<T> error(RetCodeEnum errorCodeEnum) {
		return new ApiResult<T>(errorCodeEnum.getCode(), errorCodeEnum.getDesc());
	}

	/**
	 * 错误返回
	 * 
	 * @param code
	 *            错误码
	 * @param msg
	 *            错误信息
	 * @return
	 */
	public static <T> ApiResult<T> error(String code, String msg) {
		return new ApiResult<T>(code, msg);
	}
}
