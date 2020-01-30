package com.foresee.ctrl;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foresee.utils.AliyunOSSUtil;
import com.foresee.utils.ImgUtil;
import com.foresee.utils.JSONResult;
import com.foresee.utils.StringUtil;
import com.foresee.utils.UploadUtilOss;
import com.foresee.vo.ImgPath;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 
 * 图片管理
 */
@RestController
@RequestMapping("/img")
@Validated
@Api(value="图片业务类",tags= {"图片业务API"})
public class UploadImgController {
	
	/**
	 * 上传图片
	 * @param request
	 * @param Tag
	 * @return
	 */
	/**@ApiOperation(value="上传图片base64转码",notes="上传图片base64转码接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="base64img", value="base64img",required = true,
		dataType = "String",paramType = "query")
	})
	
	@PostMapping("/uploadImageBase64")
	public JSONResult saveTag(HttpServletRequest request,
			@RequestParam("base64img") String base64img,String userId) {
		String path = BasicsFinal.WX_CONTRIBUTE_ICON;
		try {
			String uploadImage = UploadUtil.uploadImageBase64(path,base64img, userId);
			return JSONResult.ok(uploadImage);
		} catch (IOException e) {
			return JSONResult.errorMsg("");
		}
		
		
	}*/
	
	
	/**
	 * 上传图片
	 * @param request
	 * @param Tag
	 * @return
	 */
	@ApiOperation(value="上传图片base64转码",notes="上传图片base64转码接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="base64img", value="base64img",required = true,
		dataType = "String",paramType = "query")
	})
	
	@PostMapping("/uploadImageOssBase64")
	public JSONResult uploadImageOssBase64(HttpServletRequest request,
			@RequestParam("base64img") String base64img,String userId) {
		String uploadImage = UploadUtilOss.uploadImage(base64img, userId);
		return JSONResult.ok(uploadImage);
		
		
	}
	
	/**@throws Exception 
	 * @ApiOperation(value="上传图片",notes="上传图片接口")
	@ApiImplicitParam(name="userId", value="用户ID",required = true,
	dataType = "String",paramType = "form")
	@RequestMapping(value="/uploadIcon",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadIcon(String userId,@ApiParam(value="封面图",required = true) MultipartFile file) throws IOException {
		String path = BasicsFinal.WX_CONTRIBUTE_ICON;
		String uploadPath =UploadUtil.uploadImage(path, file, userId);
		return JSONResult.ok(uploadPath);
	}
	*/
	@ApiOperation(value="上传图片",notes="上传图片接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "String",paramType = "form"),
		@ApiImplicitParam(name="imageWidth", value="图片宽度",required = false,
		dataType = "int",paramType = "form"),
		@ApiImplicitParam(name="imageHeight", value="图片高度",required = false,
		dataType = "int",paramType = "form"),
	})
	@RequestMapping(value="/uploadIconScale",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadImageOssFileScale(String userId,int imageWidth, int imageHeight,@ApiParam(value="封面图",required = true) MultipartFile file) throws Exception {
		
		if(StringUtil.notBlank(imageWidth) && StringUtil.notBlank(imageHeight)) {
			BufferedImage image = ImageIO.read(file.getInputStream());
			
			if (image == null) {
				return JSONResult.errorMsg("请选择图片上传");
				
			}else {
				
				if(image.getWidth()>imageWidth || image.getHeight()>imageHeight || image.getWidth()/image.getHeight() !=1)  {
					return JSONResult.errorMsg("请选择宽高不大于"+imageWidth+"px，且1：1的图片");
				}
			}
		}
		
		String uploadPath =UploadUtilOss.uploadImage(file, userId);

		return JSONResult.ok(uploadPath);
	}
	
	@ApiOperation(value="上传图片",notes="上传图片接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "String",paramType = "form"),
		@ApiImplicitParam(name="imageWidth", value="图片宽度",required = false,
		dataType = "int",paramType = "form"),
		@ApiImplicitParam(name="imageHeight", value="图片高度",required = false,
		dataType = "int",paramType = "form"),
	})
	
	@RequestMapping(value="/uploadIconCommunity",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadImageOssFileCommunity(String userId,int imageWidth, int imageHeight,@ApiParam(value="封面图",required = true) MultipartFile file)  {
		String name= file.getOriginalFilename();
		InputStream in =null;
		String uploadPath="";
		String newFilePath="";
		try {
			if(StringUtil.notBlank(imageWidth) && StringUtil.notBlank(imageHeight)) {
				BufferedImage image = ImageIO.read(file.getInputStream());
				
				if (image == null) {
					return JSONResult.errorMsg("请选择图片上传");
					
				}else {
					
					if(image.getWidth()>imageWidth || image.getHeight()>imageHeight || image.getWidth()/image.getHeight() !=1)  {
						return JSONResult.errorMsg("请选择宽高不大于"+imageWidth+"px，且1：1的图片");
					}
				}
			}
			
			 uploadPath =UploadUtilOss.uploadImage(file, userId);
			 newFilePath = name.substring(0, name.lastIndexOf("."));
			 
			ImgUtil.cutImage(file.getInputStream(), 20, 20, 150, 76, "E:/img/"+userId+"/",newFilePath+"_2");
			String tempPath="E:/img/"+userId+"/"+newFilePath+"_2.png";
			in = new FileInputStream(tempPath);
			byte[] data = new byte[in.available()];
	        in.read(data);
	        
	        in.close();
	        String path="/"+userId+"/";
	         newFilePath = AliyunOSSUtil.uploadImg(new String(Base64.encodeBase64(data)),path);
	        
	         File f=new File(tempPath);
	         f.delete();
	        
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(in!=null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ImgPath ip =new ImgPath();
		ip.setBgImg(newFilePath);
		ip.setIconPath(uploadPath);
		return JSONResult.ok(ip);
	}
	
	@RequestMapping(value="/uploadImageOssFileUser",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadImageOssFileUser(String userId,int imageWidth, int imageHeight,@ApiParam(value="封面图",required = true) MultipartFile file)  {
		String name= file.getOriginalFilename();
		InputStream in =null;
		String uploadPath="";
		String newFilePath="";
		try {
			if(StringUtil.notBlank(imageWidth) && StringUtil.notBlank(imageHeight)) {
				BufferedImage image = ImageIO.read(file.getInputStream());
				
				if (image == null) {
					return JSONResult.errorMsg("请选择图片上传");
					
				}else {
					
					if(image.getWidth()>imageWidth || image.getHeight()>imageHeight || image.getWidth()/image.getHeight() !=1)  {
						return JSONResult.errorMsg("请选择宽高不大于"+imageWidth+"px，且1：1的图片");
					}
				}
			}
			
			 uploadPath =UploadUtilOss.uploadImage(file, userId);
			 newFilePath = name.substring(0, name.lastIndexOf("."));
			 
			ImgUtil.cutImage(file.getInputStream(), 0, 0, 75, 38, "E:/img/"+userId+"/",newFilePath+"_2");
			String tempPath="E:/img/"+userId+"/"+newFilePath+"_2.png";
			in = new FileInputStream(tempPath);
			byte[] data = new byte[in.available()];
	        in.read(data);
	        
	        in.close();
	        String path="/"+userId+"/";
	         newFilePath = AliyunOSSUtil.uploadImg(new String(Base64.encodeBase64(data)),path);
	        
	         File f=new File(tempPath);
	         f.delete();
	        
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(in!=null) {
					in.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ImgPath ip =new ImgPath();
		ip.setBgImg(newFilePath);
		ip.setIconPath(uploadPath);

		return JSONResult.ok(ip);
	}
	
	
	
	@ApiOperation(value="上传图片",notes="上传图片接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "String",paramType = "form"),
		@ApiImplicitParam(name="imageWidth", value="图片宽度",required = false,
		dataType = "int",paramType = "form"),
		@ApiImplicitParam(name="imageHeight", value="图片高度",required = false,
		dataType = "int",paramType = "form"),
	})
	
	@RequestMapping(value="/uploadIcon",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadImageOssFile(String userId,int imageWidth, int imageHeight,@ApiParam(value="封面图",required = true) MultipartFile file) throws IOException {
		
		if(StringUtil.notBlank(imageWidth) && StringUtil.notBlank(imageHeight)) {
			BufferedImage image = ImageIO.read(file.getInputStream());
			if (image == null) {
				return JSONResult.errorMsg("请选择图片上传");	
			}else {
				if(image.getWidth()!=imageWidth || image.getHeight()!=imageHeight)  {
					return JSONResult.errorMsg("请选择尺寸是"+imageWidth+"px*"+imageHeight+"px的图片");
				}
			}
		}
		
		String uploadPath =UploadUtilOss.uploadImage(file, userId);
		return JSONResult.ok(uploadPath);
	}
	
	@ApiOperation(value="上传图片",notes="上传图片接口")

	@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "String",paramType = "form")
	@RequestMapping(value="/uploadImage",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadImage(String userId,@ApiParam(value="封面图",required = true) MultipartFile file) throws IOException {

		String uploadPath =UploadUtilOss.uploadImage(file, userId);
		return JSONResult.ok(uploadPath);
	}
	
	
}
