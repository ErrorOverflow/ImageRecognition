package PictureOperation;

import java.awt.image.BufferedImage;

/**
 * @author wmlbuaa
 * @date 2018-09-02 21:03
 */
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
