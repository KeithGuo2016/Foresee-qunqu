package com.foresee.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;


import com.foresee.utils.AliyunOSSUtil;

/**
 * 图片上传
 * @author wrh
 *
 */
public class UploadUtilOss {
	
	/**
	 * 上传图片接口
	 * @param path 图片存放空间
	 * @param file 文件
	 * @param userId 用户id
	 * @return
	 */
	public static String uploadImage(String base64img,String userId){
		String path="/"+userId+"/";
		String uploadImg =AliyunOSSUtil.uploadImg(base64img, path);
		return uploadImg;
	}
	
	
	
	/**
	 * 上传图片接口
	 * @param path 图片存放空间
	 * @param file 文件
	 * @param userId 用户id
	 * @return
	 * @throws IOException 
	 */
	public static String uploadImage(MultipartFile file,String userId) throws IOException{
		String path="/"+userId+"/";
		String uploadImg = AliyunOSSUtil.uploadImg(new String(Base64.encodeBase64(file.getBytes())),path);
		return uploadImg;
	}
	
}
