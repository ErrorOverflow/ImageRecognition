package PictureOperation;

import java.awt.image.BufferedImage;

public class PictureInfo {
    private BufferedImage image;
    private int height;
    private int width;
    private int min_x;
    private int min_y;
    private int[][][] image_pixel = new int[7000][5000][3];

    public PictureInfo(BufferedImage im) {
        this.image = im;
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.min_x = image.getMinX();
        this.min_y = image.getMinY();
        transformGray_R();
    }

    public void transformGray_R(int MIN_Y, int HEIGHT, int MIN_X, int WIDTH) {

    }

    public void setImagePixel(int minx, int maxx, int miny, int maxy, int[][][] newPixel) {
        for (int i = minx; i <= maxx; i++) {
            for (int j = miny; j <= maxy; j++) {
                image_pixel[i][j][0] = newPixel[i - minx][j - miny][0];
                image_pixel[i][j][1] = newPixel[i - minx][j - miny][1];
                image_pixel[i][j][2] = newPixel[i - minx][j - miny][2];

            }
        }
    }

    public void setImage() {

    }
}

class PicThread implements Runnable {
    private BufferedImage image;
    private int minx;
    private int maxx;
    private int miny;
    private int maxy;
    private int[][][] pixel;
    private boolean isFinished;

    public PicThread(int min_x, int max_x, int min_y, int max_y, BufferedImage sourceImage) {
        this.minx = min_x;
        this.maxx = max_x;
        this.miny = min_y;
        this.maxy = max_y;
        this.image = sourceImage;
        this.isFinished = false;
    }

    public void run() {
        try {
            for (int y = miny; y < maxy; y++) {
                for (int x = minx; x < maxx; x++) {
                    //获取包含这个像素的颜色信息的值, int型
                    int pixelinfo = image.getRGB(x, y);
                    //从pixel中获取rgb的值
                    pixel[x][y][0] = (pixelinfo & 0xff0000) >> 16; //r
                    pixel[x][y][1] = (pixelinfo & 0xff00) >> 8; //g
                    pixel[x][y][2] = (pixelinfo & 0xff); //b
                }
            }
            this.isFinished = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][][] getPixel() {

    }
}
