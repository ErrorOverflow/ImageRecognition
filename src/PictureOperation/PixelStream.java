package PictureOperation;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;

/**
 * @author wmlbuaa
 * @date 2018-08-26
 */
public class PixelStream implements Runnable {
    private BufferedImage source_image;
    private Picture picture;

    public PixelStream(String image_address,Picture pic) {
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
            this.picture = new Picture(source_image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
