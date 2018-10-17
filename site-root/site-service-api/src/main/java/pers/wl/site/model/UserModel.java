/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.site.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月16日 上午11:07:05
 * @since JDK 1.8
 */
@ApiModel(value="用户基本信息demo")
public class UserModel {
	
	@ApiModelProperty("姓名")
	@Size(max = 20)
	private String name;
	
	@ApiModelProperty("年龄")
	@Max(150)
    @Min(1)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	

}
