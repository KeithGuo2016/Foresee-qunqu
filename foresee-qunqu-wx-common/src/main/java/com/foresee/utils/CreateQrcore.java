package com.foresee.utils;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.foresee.finals.BasicsFinal;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateQrcore {

    /*
     * 获取 token
　　　* 普通的 get 可获 token
     */
    public  static String getToken() {
        try {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("grant_type", "client_credential");
            map.put("appid",BasicsFinal.APPID);
            map.put("secret", BasicsFinal.SECRET);

            String rt = HttpRequest.sendPost("https://api.weixin.qq.com/cgi-bin/token", map);
            System.out.println("what is:"+rt);
            JSONObject json = JSONObject.fromObject(rt);

            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                return json.getString("access_token");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 获取 二维码图片
　　 *
     */
    public static String getminiqrQr( String accessToken,String pram,String userid) {
        String p="E://img//"+userid; //二维码生产的地址  本地F盘code文件夹
        System.out.println(p);
        String codeUrl=p+"/"+pram+".png";
        String twoCodeUrl="";
        
        InputStream in =null;
        try
        {
        	//URL url = new URL("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+accessToken);
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene",pram);//这就是你二维码里携带的参数 String型  名称不可变
            paramJson.put("page", "pages/communityDetail/index"); //这是设置扫描二维码后跳转的页面
            paramJson.put("width", 430);
           // paramJson.put("is_hyaline", true);
            paramJson.put("auto_color", true);

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            String createImgDicPath = String.format("%s", p+"/"+pram);  
            // 创建文件夹  
            File file = new File(createImgDicPath);  
            if (!file.exists()) {  
                file.mkdirs();  
            }  
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = new FileOutputStream(new File(codeUrl));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
            
           
			in = new FileInputStream(codeUrl);
			byte[] data = new byte[in.available()];
	        in.read(data);
	        
	        in.close();
	        String path="/"+userid+"/";
	        twoCodeUrl = AliyunOSSUtil.uploadImg(new String(Base64.encodeBase64(data)),path);
	         File f=new File(codeUrl);
	         f.delete();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return twoCodeUrl;
    }
    public static void main(String[] args) {
    	String accessToken = CreateQrcore.getToken();
        System.out.println("accessToken;"+accessToken);
        String twoCodeUrl = CreateQrcore.getminiqrQr(accessToken,"290","289");
        System.out.println(twoCodeUrl);
	}
}