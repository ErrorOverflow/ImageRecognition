package PictureOperation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author wmlbuaa
 * @date 2018-08-26
 */
public class Picture {
    private BufferedImage image;
    private int height;
    private int width;
    private int min_x;
    private int min_y;
    private int divx;
    private int divy;
    private int[][][] image_pixel = new int[2000][3000][3];

    private String name;

    public BufferedImage getImage() {
        return image;
    }

    public void setRGB(int x, int y, int pixel) {
        image.setRGB(x, y, pixel);
    }

    public Picture() {
    }

    public Picture(BufferedImage im) throws InterruptedException {
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
        int ComputerCore = Runtime.getRuntime().availableProcessors();
        System.out.println("start getting picture's info: " + System.currentTimeMillis());
        Lock_PicThread2PictureInfo LOCK = new Lock_PicThread2PictureInfo();
        PicThread[] threads = new PicThread[ComputerCore];
        switch (ComputerCore) {
            case 4: {
                for (int i = 0; i < ComputerCore; i++) {
                    threads[i] = new PicThread(i, min_x + divx * (i % 2), min_x + divx * (i % 2 + 1) - 1,
                            min_y + divy * (i / 2), min_y + divy * (i / 2 + 1) - 1, image, LOCK);
                    new Thread(threads[i]).start();
                }
                break;
            }
            case 6: {
                for (int i = 0; i < ComputerCore; i++) {
                    threads[i] = new PicThread(i, min_x + divx * (i % 3), min_x + divx * (i % 3 + 1) - 1,
                            min_y + divy * (i / 2), min_y + divy * (i / 2 + 1) - 1, image, LOCK);
                    new Thread(threads[i]).start();
                }
                break;
            }
            case 8: {
                for (int i = 0; i < ComputerCore; i++) {
                    threads[i] = new PicThread(i, min_x + divx * (i % 4), min_x + divx * (i % 4 + 1) - 1,
                            min_y + divy * (i / 2), min_y + divy * (i / 2 + 1) - 1, image, LOCK);
                    new Thread(threads[i]).start();
                }
                break;
            }
            case 12: {
                for (int i = 0; i < ComputerCore; i++) {
                    threads[i] = new PicThread(i, min_x + divx * (i % 4), min_x + divx * (i % 4 + 1) - 1,
                            min_y + divy * (i / 3), min_y + divy * (i / 3 + 1) - 1, image, LOCK);
                    new Thread(threads[i]).start();
                }
                break;
            }
            case 16: {
                for (int i = 0; i < ComputerCore; i++) {
                    threads[i] = new PicThread(i, min_x + divx * (i % 4), min_x + divx * (i % 4 + 1) - 1,
                            min_y + divy * (i / 4), min_y + divy * (i / 4 + 1) - 1, image, LOCK);
                    new Thread(threads[i]).start();
                }
                break;
            }
            default:{
                for (int i = 0; i < ComputerCore; i++) {
                    threads[i] = new PicThread(i, min_x + divx * (i % 1), min_x + divx * (i % 1 + 1) - 1,
                            min_y + divy * (i / 1), min_y + divy * (i / 1 + 1) - 1, image, LOCK);
                    new Thread(threads[i]).start();
                }
            }
        }
        for (int i = 0; i < ComputerCore; i++) {
            PixelBlock pixelBlock = LOCK.getInfo();
            setImagePixel(pixelBlock);
        }
        System.out.println("finished getting info: " + System.currentTimeMillis());
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

    public void readImage(String image_address) {
        File file = new File(image_address);
        if (file.exists()) {
            try {
                this.image = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveImage(String address) {
        File file = new File(address);
        try {
            ImageIO.write(this.image, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}