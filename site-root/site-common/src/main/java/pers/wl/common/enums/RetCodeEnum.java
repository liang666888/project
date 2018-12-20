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
	IMG_TYPE_ILLEGAL("0003", "文件类型不允许"),
	IMG_SIZE_EMPTY("0004", "文件大小不能为空"),
	TOKEN_EXPIRED("0005", "登录授权令牌已过期,请重新登录"),
	
	
	// 系统异常8000~9999
	SYSTEM_TIMEOUT("8000", "服务调用超时"),
	SYSTEM_ERROR("8001", "系统错误"),
	DUPLICATE_KEY_ERROR("8002","唯一性约束冲突");

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
