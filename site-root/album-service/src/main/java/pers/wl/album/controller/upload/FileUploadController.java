/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.controller.upload;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pers.wl.album.util.LogUtil;
import pers.wl.common.enums.RetCodeEnum;
import pers.wl.common.enums.exception.BizException;
import pers.wl.common.utils.result.ApiResult;
import pers.wl.common.utils.result.ApiResultUtil;

/**
 * 描述说明 文件上传
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月30日 上午9:53:27
 * @since JDK 1.8
 */
@Api(tags = "文件上传api")
@RestController
@RequestMapping("/fileupload-api")
public class FileUploadController {

	private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Value("${img.upload.path:}")
	private String imgUploadPath;

	@ApiOperation(value = "图片上传", notes = "说明：图片上传接口")
	@RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
	public ApiResult<Object> uploadImg(@RequestParam(value = "file", required = false) MultipartFile []files,
			HttpServletRequest request) throws IOException {
		for(int i=0;i<files.length;i++){
			MultipartFile file = files[i];
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String path = null;
				String type = null;
				type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
						: null;
				logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
				if (type != null) {
					if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
							|| "JPG".equals(type.toUpperCase())) {
						// 自定义的文件名称
						String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
						// 设置存放图片文件的路径
						path = imgUploadPath + File.pathSeparator + trueFileName;
						file.transferTo(new File(path));
						LogUtil.info(logger, MessageFormat.format("文件上传成功{0}", path));
					} else {
						LogUtil.warn(logger, MessageFormat.format("文件上传失败，文件类型不允许：{0}", type));
					}
				} else {
					LogUtil.warn(logger, "文件上传失败，文件类型为空");
				}
			} else {
				LogUtil.warn(logger, "文件上传失败，文件内容为空");
			}
		}
		return ApiResultUtil.success();
	}

}
