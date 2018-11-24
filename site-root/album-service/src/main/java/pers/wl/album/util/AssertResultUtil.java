/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.util;

import java.util.Map;

import org.springframework.util.Assert;

import pers.wl.common.enums.RetCodeEnum;
import pers.wl.common.enums.exception.BizException;

/**
 * 描述说明 方法/接口请求结果合法性判断工具类
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年11月24日 下午2:11:47
 * @since JDK 1.8
 */
public class AssertResultUtil {

	public static void isNull(Object object, RetCodeEnum resultCode) {
		try {
			Assert.isNull(object, resultCode.getDesc());
		} catch (RuntimeException e) {
			throw new BizException(resultCode);
		}
	}

	public static void isNull(Object object, RetCodeEnum resultCode, String message) {
		try {
			Assert.isNull(object, message);
		} catch (RuntimeException e) {
			throw new BizException(resultCode, message);
		}
	}

	public static void notNull(Object object, RetCodeEnum resultCode) {
		try {
			Assert.notNull(object, resultCode.getDesc());
		} catch (RuntimeException e) {
			throw new BizException(resultCode);
		}
	}

	public static void notNull(Object object, RetCodeEnum resultCode, String message) {
		try {
			Assert.notNull(object, message);
		} catch (RuntimeException e) {
			throw new BizException(resultCode, message);
		}
	}

	public static void notBlank(String string, RetCodeEnum resultCode) {
		try {
			Assert.hasText(string, resultCode.getDesc());
		} catch (RuntimeException e) {
			throw new BizException(resultCode);
		}
	}

	public static void notBlank(String string, RetCodeEnum resultCode, String message) {
		try {
			Assert.hasText(string, message);
		} catch (RuntimeException e) {
			throw new BizException(resultCode, message);
		}
	}

	public static void notEmpty(Map<String, String> map, RetCodeEnum resultCode) {
		try {
			Assert.notEmpty(map, resultCode.getDesc());
		} catch (RuntimeException e) {
			throw new BizException(resultCode);
		}
	}

	public static void notEmpty(Map<String, String> map, RetCodeEnum resultCode, String message) {
		try {
			Assert.notEmpty(map, message);
		} catch (RuntimeException e) {
			throw new BizException(resultCode, message);
		}
	}

	public static void isTrue(boolean boolValue, RetCodeEnum resultCode) {
		try {
			Assert.isTrue(boolValue, resultCode.getDesc());
		} catch (RuntimeException e) {
			throw new BizException(resultCode);
		}
	}

	public static void isTrue(boolean boolValue, RetCodeEnum resultCode, String message) {
		try {
			Assert.isTrue(boolValue, message);
		} catch (RuntimeException e) {
			throw new BizException(resultCode, message);
		}
	}

	public static void isTrue(boolean boolValue, BizException bizException) {
		try {
			Assert.isTrue(boolValue, bizException.getMessage());
		} catch (RuntimeException e) {
			throw bizException;
		}
	}

	public static void equals(String actualResult, String expectResult, RetCodeEnum resultCode) {
		if (null == actualResult && null != expectResult) {
			throw new BizException(resultCode);
		}

		try {
			if (null != actualResult) {
				Assert.isTrue(actualResult.equals(expectResult), resultCode.getDesc());
			}
		} catch (RuntimeException e) {
			throw new BizException(resultCode);
		}
	}

	public static void equals(String actualResult, String expectResult, BizException bizException) {
		if (null == actualResult && null != expectResult) {
			throw bizException;
		}

		try {
			if (null != actualResult) {
				Assert.isTrue(actualResult.equals(expectResult), bizException.getMessage());
			}
		} catch (RuntimeException e) {
			throw bizException;
		}
	}

	public static void equals(String actualResult, String expectResult, RetCodeEnum resultCode, String message) {
		if (null == actualResult && null != expectResult) {
			throw new BizException(resultCode);
		}

		try {
			if (null != actualResult) {
				Assert.isTrue(actualResult.equals(expectResult), message);
			}
		} catch (RuntimeException e) {
			throw new BizException(resultCode, message);
		}
	}

	public static void bigger(int actualResult, int expectResult, RetCodeEnum resultCode) {
		if (actualResult < expectResult) {
			throw new BizException(resultCode);
		}
	}

	public static void bigger(int actualResult, int expectResult, BizException bizException) {
		if (actualResult < expectResult) {
			throw bizException;
		}
	}

	public static void bigger(int actualResult, int expectResult, RetCodeEnum resultCode, String message) {
		if (actualResult < expectResult) {
			throw new BizException(resultCode, message);
		}
	}

}
