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
		new WatermarkImgUtils().addWatermark("E:\\test/ATS-A.png", "E:\\test/1.png", "��������OCC����������A", "png");
		System.out.println("水印完成");
	}

	/**
	 * @description
	 * @param sourceImgPath    ԴͼƬ·��
	 * @param tarImgPath       �����ͼƬ·��
	 * @param waterMarkContent ˮӡ����
	 * @param fileExt          ͼƬ��ʽ
	 * @return void
	 */
	public void addWatermark(String sourceImgPath, String tarImgPath, String waterMarkContent, String fileExt) {
		Font font = new Font("����", Font.BOLD, 10);// ˮӡ���壬��С
		Color markContentColor = Color.BLACK;// ˮӡ��ɫ
		Integer degree = 45;// ����ˮӡ���ֵ���ת�Ƕ�
		float alpha = 1f;// ����ˮӡ͸����
		OutputStream outImgStream = null;
		try {
			File srcImgFile = new File(sourceImgPath);// �õ��ļ�
			Image srcImg = ImageIO.read(srcImgFile);// �ļ�ת��ΪͼƬ
			int srcImgWidth = srcImg.getWidth(null);// ��ȡͼƬ�Ŀ�
			int srcImgHeight = srcImg.getHeight(null);// ��ȡͼƬ�ĸ�
			// ��ˮӡ
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();// �õ�����
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			g.setColor(markContentColor); // ����ˮӡ��ɫ
			g.setFont(font); // ��������
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));// ����ˮӡ����͸����
			if (null != degree) {
				g.rotate(Math.toRadians(degree));// ����ˮӡ��ת
			}
			JLabel label = new JLabel(waterMarkContent);
			FontMetrics metrics = label.getFontMetrics(font);
			int width = metrics.stringWidth(label.getText());// ����ˮӡ�Ŀ�
			int rowsNumber = srcImgHeight / width;// ͼƬ�ĸ� ���� ����ˮӡ�Ŀ� ����> ��ӡ������(������ˮӡ�Ŀ�Ϊ���)
			int columnsNumber = srcImgWidth / width;// ͼƬ�Ŀ� ���� ����ˮӡ�Ŀ� ����> ÿ�д�ӡ������(������ˮӡ�Ŀ�Ϊ���)
			// ��ֹͼƬ̫С������ˮӡ̫�����������ٴ�ӡһ��
        	if(rowsNumber < 1){
        		rowsNumber = 1;
        	}
        	if(columnsNumber < 1){
        		columnsNumber = 1;
        	}
        	for(int j=0;j<rowsNumber;j++){
        		for(int i=0;i<columnsNumber;i++){
            		g.drawString(waterMarkContent, i*width + j*width, -i*width + j*width);//����ˮӡ,������ˮӡλ��
            	}
        	}
//			g.drawString(waterMarkContent, 35, 35);// ����ˮӡ,������ˮӡλ��
			g.dispose();// �ͷ���Դ
			// ���ͼƬ
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