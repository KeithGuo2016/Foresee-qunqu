package com.foresee.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import com.aliyun.oss.OSSClient;
/**
 * 阿里云OSS
 * @author wrh
 */
public class AliyunOSSUtil {
	//oss秘钥
	public static final String ACCESSKEYID="LTAI92gHh4s0aRuW";
	public static final String ACCESSKEYSECRET="ncAaIocNBoaOg5bXySeVGA280bmgHj";
	public static final String ENDPOINT="https://oss-cn-beijing.aliyuncs.com/";
	public static final String BUCKETNAME="qqxw-pics";
	public static final String OSSURL="https://qqxw-pics.oss-cn-beijing.aliyuncs.com/";
	/**
	 * 图片上传
	 * @param imgdata
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String uploadImg(String imgdata,String path){
		try {
			String endpoint = ENDPOINT;
			String accessKeyId = ACCESSKEYID;
			String accessKeySecret = ACCESSKEYSECRET;
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			InputStream inputStream =Base64.baseToInputStream(imgdata);
			String fileExtendName = getFileExtendName(imgdata.getBytes());
			String imgName=RandomUtil.randomImgName().substring(0, RandomUtil.randomImgName().lastIndexOf("."));
			
			String randomImgName=imgName+fileExtendName;
			ossClient.putObject(BUCKETNAME,"IMAGE"+path+randomImgName , inputStream);
			ossClient.shutdown();
			return AliyunOSSUtil.OSSURL+"IMAGE"+path+randomImgName;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
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
