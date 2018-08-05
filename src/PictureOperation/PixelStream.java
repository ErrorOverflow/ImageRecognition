package PictureOperation;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;

public class PixelStream implements Runnable {
    private BufferedImage source_image;

    public PixelStream(String image_address) {
        File file = new File(image_address);
        if (file.exists()) {
            try {
                source_image = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        try {
            System.out.println(System.currentTimeMillis());
            PictureInfo pic = new PictureInfo(source_image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
