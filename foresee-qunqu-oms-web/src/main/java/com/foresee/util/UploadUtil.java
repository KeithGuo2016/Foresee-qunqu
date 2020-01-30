package com.foresee.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.foresee.exception.ResultException;
import com.foresee.utils.AliyunOSSUtil;
import com.foresee.utils.ResultCode;
/**
 * 图片上传
 * @author wrh
 *
 */
public class UploadUtil {
	
	/**
	 * 上传图片接口
	 * @param path 图片存放空间
	 * @param file 文件
	 * @param userId 用户id
	 * @return
	 */
	public static String uploadImage(String base64img,String userId){
		String path="/"+userId+"/";
		String uploadImg = "";
		try {
			/*InputStream inputStream =Base64.baseToInputStream(base64img);
			// 构造Image对象  
	        java.awt.image.BufferedImage src = javax.imageio.ImageIO.read(inputStream);  
	        int width = src.getWidth();  
	        int height = src.getHeight(); */
			uploadImg = AliyunOSSUtil.uploadImg(base64img, path);
		} catch (Exception e) {
			throw new ResultException(ResultCode.IMG_ERROR);
		}
		return uploadImg;
	}
	
	
	
	/**
	 * 上传图片接口
	 * @param path 图片存放空间
	 * @param file 文件
	 * @param userId 用户id
	 * @return
	 */
	public static String uploadImage(MultipartFile file,String userId){
		String path="/"+userId+"/";
		String uploadImg = "";
		try {
			uploadImg = AliyunOSSUtil.uploadImg(new String(Base64.encodeBase64(file.getBytes())),path);
		} catch (Exception e) {
			throw new ResultException(ResultCode.IMG_ERROR);
		}
		return uploadImg;
	}
}
