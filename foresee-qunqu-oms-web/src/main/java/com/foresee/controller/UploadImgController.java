package com.foresee.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foresee.constant.UploadImgCofig;
import com.foresee.model.User;
import com.foresee.util.UploadUtil;
import com.foresee.utils.Base64;
import com.foresee.utils.ResultUtils;

/**
 * @author wangruiheng
 * 图片管理
 */
@RestController
@RequestMapping("/img")
@Validated
public class UploadImgController {
	
	/**
	 * 上传图片
	 * @param request
	 * @return
	 */
	@PostMapping("/upload")
	public Object saveImg(HttpServletRequest request,
			@RequestParam("base64img") String base64img) {
		User account =  (User) request.getSession().getAttribute("accounts");
		String uploadImage = UploadUtil.uploadImage(base64img, account.getId().toString());
		return ResultUtils.success(uploadImage);
		
		
	}
	
	/**
	 * 上传图片
	 * @param request
	 * @return
	 */
	@PostMapping("/upload2")
	public Object saveImg2(HttpServletRequest request,
			@RequestParam("file") MultipartFile file) {
		User account =  (User) request.getSession().getAttribute("accounts");
		//获取上传图片的文件名,变为时间+图片名
		System.out.println(file.getOriginalFilename());
		String uploadImage = UploadUtil.uploadImage(file, account.getId().toString());
		Map<String, Object> map = new HashMap<>();
		
		map.put("src", uploadImage);
		
		return ResultUtils.success(map);
	}
	
	
	/**
	 * 检查图片
	 * @param request
	 * @return
	 */
	@PostMapping("/uploadcheck")
	public Object saveImg(HttpServletRequest request,
			@RequestParam("base64img") String base64img,@RequestParam("type") int type) {
		boolean fa = false;
		try {
			InputStream inputStream =Base64.baseToInputStream(base64img);
			// 构造Image对象  
	        java.awt.image.BufferedImage src = ImageIO.read(inputStream);  
	        int width = src.getWidth();  
	        int height = src.getHeight(); 
	        switch (type) {
			case 1:
				fa = width < UploadImgCofig.IMG_1_WIDTH;
				fa = height < UploadImgCofig.IMG_1_HEIGHT;
				break;
			case 2:
				fa = width < UploadImgCofig.IMG_2_WIDTH;
				fa = height < UploadImgCofig.IMG_2_HEIGHT;
				break;
			case 3:
				fa = width < UploadImgCofig.IMG_3_WIDTH;
				fa = height < UploadImgCofig.IMG_3_HEIGHT;
				break;
			case 4:
				fa = width < UploadImgCofig.IMG_4_WIDTH;
				fa = height < UploadImgCofig.IMG_4_HEIGHT;
				break;
			case 5:
				fa = width < UploadImgCofig.IMG_5_WIDTH;
				fa = height < UploadImgCofig.IMG_5_HEIGHT;
				break;
			case 6:
				fa = width < UploadImgCofig.IMG_6_WIDTH;
				fa = height < UploadImgCofig.IMG_6_HEIGHT;
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return fa;
	}
}
