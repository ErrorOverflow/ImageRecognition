package Ashing;

import PictureOperation.PictureInfo;

import java.awt.image.BufferedImage;

class Ashing {
    public Ashing(PictureInfo PicInfo) {
        BufferedImage image;
        try {
            image = PicInfo.getImage();
            for (int y = image.getMinY(); y < image.getHeight(); y++) {
                for (int x = image.getMinX(); x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    int r = (pixel >> 16) & 0x00ff;
                    pixel = (r & 0x000000ff) | (pixel & 0xffffff00); //用r的值设置b的值
                    pixel = ((r << 8) & 0x0000ff00) | (pixel & 0xffff00ff);//用r的值设置g的值
                    PicInfo.setRGB(x, y, pixel);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}