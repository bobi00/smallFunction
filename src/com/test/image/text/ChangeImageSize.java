package com.test.image.text;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ChangeImageSize {

	
	public static void main(String[] args) {
		String str = System.getProperty("user.dir") + "\\image\\1.jpg";
		System.out.println(str);
		changeSize(25,35, str);
	}
	
	/**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public static boolean changeSize(int newWidth, int newHeight, String path) {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));

            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
            //输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(tag);
            ImageIO.write(tag, "PNG", out);
            System.out.println(path);
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
