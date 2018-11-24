/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.album.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 描述说明 文件上传
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年10月30日 上午9:53:27
 * @since JDK 1.8
 */
@Api(tags="文件上传api")
@RestController
@RequestMapping("/fileupload-api")
public class FileUploadController {
	
	private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@ApiOperation(value = "图片上传", notes = "说明：图片上传接口")
	@RequestMapping(value="/uploadimg", method = RequestMethod.POST)
	public String uploadImg(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        if(!file.isEmpty()) {
            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    path = realPath + "/uploads/" + trueFileName;
                    logger.info("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));
                    logger.info("文件成功上传到指定目录下");
                }else {
                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                    return "error";
                }
            }else {
                logger.info("文件类型为空");
                return "error";
            }
        }else {
            logger.info("没有找到相对应的文件");
            return "error";
        }
        return "success";
    }

}
