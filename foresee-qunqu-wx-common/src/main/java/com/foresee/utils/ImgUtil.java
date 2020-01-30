package com.foresee.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;



public class ImgUtil {
	
	public static void toBDFile(String urlStr, String bdUrl) throws IOException{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		DataInputStream in = new DataInputStream(conn.getInputStream());
        byte[] data=toByteArray(in);
        in.close();
        FileOutputStream out=new FileOutputStream(bdUrl);
        out.write(data);
        out.close();
	}
	
	public static byte[] toByteArray(InputStream in) throws IOException {
		
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024*4];
        int n=0;
        while ( (n=in.read(buffer)) !=-1) {
            out.write(buffer,0,n);
        }
        return out.toByteArray();
	}


	/**
	 * 获取网络文件的输入流
	 * @param urlStr
	 * @return
	 */
	public static InputStream getInputStreamByUrl(String urlStr){
		DataInputStream in = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			in = new DataInputStream(conn.getInputStream());
		} catch (IOException e) {
			
		}    
		return in;
	}


	
/**
 * 
 * @param filePath图片路径
 * @param x 截取开始x轴
 * @param y 截取开始Y轴
 * @param w 截取宽度
 * @param h 截取高度
 * @param newFilePath 新图片路径
 * @throws Exception
 */
	public static void cutImage(InputStream filePath, int x, int y, int w, int h,String newFilePath,String name )
            throws Exception {
            ImageInputStream iis = ImageIO.createImageInputStream(filePath);
            @SuppressWarnings("rawtypes")
            Iterator it = ImageIO.getImageReaders(iis);
            ImageReader imagereader = (ImageReader) it.next();
            imagereader.setInput(iis);
            ImageReadParam par = imagereader.getDefaultReadParam();
            par.setSourceRegion(new Rectangle(x, y, w, h));
            BufferedImage bi = imagereader.read(0, par);
            String createImgDicPath = String.format("%s", newFilePath);  
            // 创建文件夹  
            File file = new File(createImgDicPath);  
            if (!file.exists()) {  
                file.mkdirs();  
            }  
            ImageIO.write(bi, "png", new File(createImgDicPath+name+".png"));
            createThumb2(createImgDicPath+name+".png",createImgDicPath+name+".png",w*5,h*5);
        }
	
	public static void cutImage(String filePath, int x, int y, int w, int h,String newFilePath )
            throws Exception {
            ImageInputStream iis = ImageIO.createImageInputStream(new FileInputStream(filePath));
            @SuppressWarnings("rawtypes")
            Iterator it = ImageIO.getImageReaders(iis);
            ImageReader imagereader = (ImageReader) it.next();
            imagereader.setInput(iis);
            ImageReadParam par = imagereader.getDefaultReadParam();
            par.setSourceRegion(new Rectangle(x, y, w, h));
            BufferedImage bi = imagereader.read(0, par);
            ImageIO.write(bi, "png", new File(newFilePath));
        }

    
    /** 
     * 第一步，等比例压缩图片，第二部，截取图片（以左上角x=0 y=0,截取） 
     *  
     * @param srcImgPath 
     *            待切割图片路径 
     * @param destImgPath 
     *            切割后图片路径 
     * @param destImgW 
     *            所需宽度 
     * @param destImgH 
     *            所需高度 
     * @throws IOException 
     */  
    public static void createThumb2(String srcImgPath, String destImgPath, int destImgW, int destImgH) throws IOException {  
  
        // 原图片等比例缩小或放大之后的图片  
        int narrowImgW;  
        int narrowImgH;  
  
        // 原图片大小  
        int srcImgW;  
        int srcImgH;  
  
        // try {  
        BufferedImage bi = ImageIO.read(new File(srcImgPath));  
        srcImgW = bi.getWidth();  
        srcImgH = bi.getHeight();  
  
        // 转换图片尺寸与目标尺寸比较 ， 如果转换图片较小，说明转换图片相对于目标图片来说高较小，需要以高为基准进行缩放。  
        if ((float) srcImgW / srcImgH > (float) destImgW / destImgH) {  
            narrowImgW = (int) (((float) destImgH / (float) srcImgH) * srcImgW);  
            narrowImgH = destImgH;  
            // 按照原图以高为基准等比例缩放、或放大。这一步高为所需图片的高度，宽度肯定会比目标宽度宽。  
            BufferedImage narrowImg = new BufferedImage(narrowImgW, narrowImgH, BufferedImage.TYPE_INT_RGB);  
            narrowImg.getGraphics().drawImage(bi.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_SMOOTH), 10, 10, null);  
  
            // 等比例缩放完成后宽度与目标尺寸宽度相比较 ， 将多余宽的部分分为两份 ，左边删除一部分  
            Image image = narrowImg.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_DEFAULT);  
  
            // 获取需要部分  
            CropImageFilter cropFilter = new CropImageFilter(0, 0, narrowImgW, destImgH);  
            Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));  
            BufferedImage cutRightNarrowImg = new BufferedImage(narrowImgW, destImgH, BufferedImage.TYPE_INT_RGB);  
  
            Graphics g = cutRightNarrowImg.getGraphics();  
            g.drawImage(img, 0, 0, null); // 绘制截取后的图  
            g.dispose();  
  
            // 输出为文件 最终为所需要的格式  
            ImageIO.write(cutRightNarrowImg, "JPEG", new File(destImgPath));  
  
        } else { // 以宽度为基准  
            narrowImgW = destImgW;  
            narrowImgH = (int) (((float) destImgW / (float) srcImgW) * srcImgH);  
  
            BufferedImage narrowImg = new BufferedImage(narrowImgW, narrowImgH, BufferedImage.TYPE_INT_RGB);  
            narrowImg.getGraphics().drawImage(bi.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_SMOOTH), 0, 0, null);  
  
            Image image = narrowImg.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_DEFAULT);// 等比例压缩  
  
            // 获取需要部分  
            CropImageFilter cropFilter1 = new CropImageFilter(0, 0, narrowImgW, destImgH);  
            Image img1 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter1));  
            BufferedImage cutBottomNarrowImg1 = new BufferedImage(narrowImgW, destImgH, BufferedImage.TYPE_INT_RGB);  
  
            Graphics g = cutBottomNarrowImg1.getGraphics();  
            g.drawImage(img1, 0, 0, null);  
            g.dispose();  
            ImageIO.write(cutBottomNarrowImg1, "JPEG", new File(destImgPath));  
        }  
        
    }  
	 public static void main(String[] args) throws Exception {  
		 InputStream in = new FileInputStream("E:\\face\\22.png");
	     cutImage(in,0,0,75,38,"E:\\img\\","33");
	        
	  
	    } 
}
