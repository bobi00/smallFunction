package com.test.image.text;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class WatermarkImgUtils {

	public static void main(String[] args) {
		System.out.println("开始水印");
		new WatermarkImgUtils().addWatermark("E:\\test/ATS-A.png", "E:\\test/1.png", "锟斤拷锟斤拷锟斤拷锟斤拷OCC锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷A", "png");
		System.out.println("水印结束");
	}

	/**
	 * @description
	 * @param sourceImgPath    源图片路锟斤拷
	 * @param tarImgPath       锟斤拷锟斤拷锟酵计凤拷锟�
	 * @param waterMarkContent 水印锟斤拷锟斤拷
	 * @param fileExt          图片锟斤拷式
	 * @return void
	 */
	public void addWatermark(String sourceImgPath, String tarImgPath, String waterMarkContent, String fileExt) {
		Font font = new Font("锟斤拷锟斤拷", Font.BOLD, 10);// 水印锟斤拷锟藉，锟斤拷小
		Color markContentColor = Color.BLACK;// 水印锟斤拷色
		Integer degree = 45;// 锟斤拷锟斤拷水印锟斤拷锟街碉拷锟斤拷转锟角讹拷
		float alpha = 1f;// 锟斤拷锟斤拷水印透锟斤拷锟斤拷
		OutputStream outImgStream = null;
		try {
			File srcImgFile = new File(sourceImgPath);// 锟矫碉拷锟侥硷拷
			Image srcImg = ImageIO.read(srcImgFile);// 锟侥硷拷转锟斤拷为图片
			int srcImgWidth = srcImg.getWidth(null);// 锟斤拷取图片锟侥匡拷
			int srcImgHeight = srcImg.getHeight(null);// 锟斤拷取图片锟侥革拷
			// 锟斤拷水印
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();// 锟矫碉拷锟斤拷锟斤拷
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			g.setColor(markContentColor); // 锟斤拷锟斤拷水印锟斤拷色
			g.setFont(font); // 锟斤拷锟斤拷锟斤拷锟斤拷
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));// 锟斤拷锟斤拷水印锟斤拷锟斤拷透锟斤拷锟斤拷
			if (null != degree) {
				g.rotate(Math.toRadians(degree));// 锟斤拷锟斤拷水印锟斤拷转
			}
			JLabel label = new JLabel(waterMarkContent);
			FontMetrics metrics = label.getFontMetrics(font);
			int width = metrics.stringWidth(label.getText());// 锟斤拷锟斤拷水印锟侥匡拷
			int rowsNumber = srcImgHeight / width;// 图片锟侥革拷 锟斤拷锟斤拷 锟斤拷锟斤拷水印锟侥匡拷 锟斤拷锟斤拷> 锟斤拷印锟斤拷锟斤拷锟斤拷(锟斤拷锟斤拷锟斤拷水印锟侥匡拷为锟斤拷锟�)
			int columnsNumber = srcImgWidth / width;// 图片锟侥匡拷 锟斤拷锟斤拷 锟斤拷锟斤拷水印锟侥匡拷 锟斤拷锟斤拷> 每锟叫达拷印锟斤拷锟斤拷锟斤拷(锟斤拷锟斤拷锟斤拷水印锟侥匡拷为锟斤拷锟�)
			// 锟斤拷止图片太小锟斤拷锟斤拷锟斤拷水印太锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟劫达拷印一锟斤拷
        	if(rowsNumber < 1){
        		rowsNumber = 1;
        	}
        	if(columnsNumber < 1){
        		columnsNumber = 1;
        	}
        	for(int j=0;j<rowsNumber;j++){
        		for(int i=0;i<columnsNumber;i++){
            		g.drawString(waterMarkContent, i*width + j*width, -i*width + j*width);//锟斤拷锟斤拷水印,锟斤拷锟斤拷锟斤拷水印位锟斤拷
            	}
        	}
//			g.drawString(waterMarkContent, 35, 35);// 锟斤拷锟斤拷水印,锟斤拷锟斤拷锟斤拷水印位锟斤拷
			g.dispose();// 锟酵凤拷锟斤拷源
			// 锟斤拷锟酵计�
			outImgStream = new FileOutputStream(tarImgPath);
			ImageIO.write(bufImg, fileExt, outImgStream);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			try {
				if (outImgStream != null) {
					outImgStream.flush();
					outImgStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}
		}
	}
}