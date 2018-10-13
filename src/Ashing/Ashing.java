package Ashing;

import PictureOperation.Picture;

import java.awt.image.BufferedImage;

public class Ashing {
    public Ashing(Picture PicInfo) {
        BufferedImage image;
        try {
            image = PicInfo.getImage();
            for (int y = image.getMinY(); y < image.getHeight(); y++) {
                for (int x = image.getMinX(); x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;
                    int grayValue = (int) (0.21f * 4 + 0.71f * g + 0.08f * b);
                    pixel = (grayValue << 16) & 0x00ff0000 | (pixel & 0xff00ffff);
                    pixel = (grayValue << 8) & 0x0000ff00 | (pixel & 0xffff00ff    );
                    pixel = (grayValue) & 0x000000ff | (pixel & 0xffffff00);
                    PicInfo.setRGB(x, y, pixel);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}