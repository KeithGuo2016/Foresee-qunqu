package com.foresee.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	/**
	 * 上传图片接口
	 * @param path 图片存放空间
	 * @param file 文件
	 * @param userId 用户id
	 * @return
	 * @throws IOException 
	 */
	public static String uploadImage(String path,MultipartFile file,String userId) throws IOException {
		//图片存放空间
		String fileSpase = path;
		//数据库保存路径
		String uploadDBPath="/"+userId;
		
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		
		try {
			if(file != null ) {
				
				String fileName= file.getOriginalFilename();
				
				if(StringUtil.notBlank(fileName)) {
					//文件上传的最终路径
					fileSpase=fileSpase+uploadDBPath+"/"+fileName;
					//数据库保存路径
					uploadDBPath+=("/"+fileName);
					File outFile= new File(fileSpase);
					if(outFile.getParentFile() != null ||!outFile.getParentFile().isDirectory()) {
						//创建文件夹
						outFile.getParentFile().mkdirs();	
					}
					fileOutputStream = new FileOutputStream(outFile);
					inputStream = file.getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				}
				
			}else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}finally {
			if(fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
			if(inputStream != null) {
				inputStream.close();
			}
		}
		return "/img"+uploadDBPath;
	}
	
	public static String uploadImageBase64(String path, String base64img,String userId) throws IOException {
		//图片存放空间
		String fileSpase = path;
		//数据库保存路径
		String uploadDBPath="/"+userId;
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		String fileName = RandomUtil.randomImgName();
		try {
			if(StringUtil.notBlank(base64img) ) {
				String fileExtendName = getFileExtendName(base64img.getBytes());
				//文件上传的最终路径
				fileSpase=fileSpase+uploadDBPath+"/"+fileName+fileExtendName;
				//数据库保存路径
				uploadDBPath+=("/"+fileName+fileExtendName);
				File outFile= new File(fileSpase);
				if(outFile.getParentFile() != null ||!outFile.getParentFile().isDirectory()) {
					//创建文件夹
					outFile.getParentFile().mkdirs();	
				}
				fileOutputStream = new FileOutputStream(outFile);
				inputStream =Base64.baseToInputStream(base64img);
				IOUtils.copy(inputStream, fileOutputStream);
				
			}else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}finally {
			if(fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
			if(inputStream != null) {
				inputStream.close();
			}
		}
		return "/img"+uploadDBPath;
	}
	
	
	
	/**
     * 根据得到图片字节，获得图片后缀
     *
     * @param photoByte 图片字节
     * @return 图片后缀
     */
    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = ".jpg";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70)
                && (photoByte[3] == 56) && ((photoByte[4] == 55) || (photoByte[4] == 57))
                && (photoByte[5] == 97)) {
            strFileExtendName = ".gif";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73)
                && (photoByte[9] == 70)) {
            strFileExtendName = ".jpg";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            strFileExtendName = ".bmp";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            strFileExtendName = ".png";
        }
        return strFileExtendName;
    }
}
