package PictureOperation;

import java.awt.image.BufferedImage;

public class PictureInfo {
    private BufferedImage image;
    private int height;
    private int width;
    private int min_x;
    private int min_y;
    private int divx;
    private int divy;
    private int[][][] image_pixel = new int[2000][3000][3];

    public BufferedImage getImage() {
        return image;
    }

    public void setRGB(int x, int y, int pixel) {
        image.setRGB(x, y, pixel);
    }

    public PictureInfo(BufferedImage im) throws InterruptedException {
        this.image = im;
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.min_x = image.getMinX();
        this.min_y = image.getMinY();
        this.divy = (height - min_y) / 4;
        this.divx = (width - min_x) / 4;
        transformGray_R();
    }

    public void transformGray_R() throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        Lock_PicThread2PictureInfo LOCK = new Lock_PicThread2PictureInfo();
        PicThread[] threads = new PicThread[16];
        for (int i = 0; i < 16; i++) {
            threads[i] = new PicThread(i, min_x + divx * (i % 4), min_x + divx * (i % 4 + 1) - 1, min_y + divy * (i / 4), min_y + divy * (i / 4 + 1) - 1, image, LOCK);
            new Thread(threads[i]).start();
        }
        for (int i = 0; i < 16; i++) {
            PixelBlock pixelBlock = LOCK.getInfo();
            setImagePixel(pixelBlock);
        }
        System.out.println(System.currentTimeMillis());
    }

    public void setImagePixel(PixelBlock newPixel) {
        int minx = min_x + divx * (newPixel.getNum() % 4);
        int maxx = min_x + divx * (newPixel.getNum() % 4 + 1) - 1;
        int miny = min_y + divy * (newPixel.getNum() / 4);
        int maxy = min_y + divy * (newPixel.getNum() / 4 + 1) - 1;
        for (int i = minx; i <= maxx; i++) {
            for (int j = miny; j <= maxy; j++) {
                image_pixel[i][j][0] = newPixel.getInfo()[i - minx][j - miny][0];
                image_pixel[i][j][1] = newPixel.getInfo()[i - minx][j - miny][1];
                image_pixel[i][j][2] = newPixel.getInfo()[i - minx][j - miny][2];
            }
        }
    }
}

//width:1792 height:1198
class PicThread implements Runnable {
    private BufferedImage image;
    private int num;
    private int minx;
    private int maxx;
    private int miny;
    private int maxy;
    private int[][][] pixel = new int[1000][1000][3];
    private Lock_PicThread2PictureInfo lock;

    public PicThread(int n, int min_x, int max_x, int min_y, int max_y, BufferedImage sourceImage, Lock_PicThread2PictureInfo LOCK) {
        this.num = n;
        this.minx = min_x;
        this.maxx = max_x;
        this.miny = min_y;
        this.maxy = max_y;
        this.image = sourceImage;
        this.lock = LOCK;
    }

    public void run() {
        try {
            //System.out.println(minx + " " + maxx + " " + miny + " " + maxy);
            for (int y = miny; y <= maxy; y++) {
                for (int x = minx; x <= maxx; x++) {
                    //获取包含这个像素的颜色信息的值, int型
                    int pixelinfo = image.getRGB(x, y);
                    //从pixel中获取rgb的值
                    pixel[x - minx][y - miny][0] = (pixelinfo & 0xff0000) >> 16; //r
                    pixel[x - minx][y - miny][1] = (pixelinfo & 0xff00) >> 8; //g
                    pixel[x - minx][y - miny][2] = (pixelinfo & 0xff); //b
                }
            }
            PixelBlock pixelBlock = new PixelBlock(num, pixel);

            lock.setInfo(pixelBlock);
        } catch (Exception e) {
            System.out.println(num);
            e.printStackTrace();
        }
    }
}

class Lock_PicThread2PictureInfo {
    private boolean lock = false;
    private PixelBlock info;

    public synchronized void setInfo(PixelBlock newPixel) throws InterruptedException {
        while (lock) {
            wait();
        }
        info = newPixel;
        lock = true;
        notifyAll();
    }

    public synchronized PixelBlock getInfo() throws InterruptedException {
        while (!lock) {
            wait();
        }
        PixelBlock mid = info;
        lock = false;
        notifyAll();
        return mid;
    }
}

class PixelBlock {
    int num;
    private int[][][] info;

    public PixelBlock(int n, int[][][] inf) {
        this.num = n;
        this.info = inf;
    }

    public int getNum() {
        return num;
    }

    public int[][][] getInfo() {
        return info;
    }
}
