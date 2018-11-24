package pers.wl.common.enums;

/**
 * 
 * 描述说明
 * rest请求结果码枚举
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年11月24日 上午11:56:02
 * @since JDK 1.8
 */
public enum RetCodeEnum {
	// 业务提示0000~7999
	SUCCESS("0000", "请求处理成功"),
	REQUEST_ILLEGAL("0001", "请求报文非法"),
	PARAM_ILLEGAL("0002", "参数校验未通过"),
	
	
	// 系统异常8000~9999
	SYSTEM_TIMEOUT("8000", "服务调用超时"),
	SYSTEM_ERROR("8001", "系统错误");

	private final String code;

	private final String desc;

	RetCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static RetCodeEnum getResultCode(String code) {
		for (RetCodeEnum resultCode : values()) {
			if (resultCode.getCode().equals(code)) {
				return resultCode;
			}
		}
		return SYSTEM_ERROR;
	}
}
