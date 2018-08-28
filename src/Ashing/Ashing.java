package Ashing;

import PictureOperation.PictureInfo;

class Ashing {
    private PictureInfo picinfo;

    public Ashing(PictureInfo PicInfo) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
            for(int y = image.getMinY(); y  < image.getHeight(); y++) {
                for(int x = image.getMinX(); x < image.getWidth(); x ++) {
                    int pixel = image.getRGB(x, y);
                    int r = (pixel >> 16) & 0x00ff;
                    pixel = (r & 0x000000ff) | (pixel & 0xffffff00); //用r的值设置b的值
                    pixel = ((r<<8) & 0x0000ff00) | (pixel & 0xffff00ff);//用r的值设置g的值
                    image.setRGB(x, y, pixel);
                }
            }
            try {
                ImageIO.write(image, "jpg", new File(path));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}