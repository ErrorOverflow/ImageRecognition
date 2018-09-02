package PictureOperation;

import base.SavePath;
import base.SupportFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author wmlbuaa
 * @date 2018-09-02 21:04
 */
class PictureSave {
    public void save(BufferedImage image, String save_arg) throws IOException {
        switch (save_arg) {
            case (SupportFormat.FORMAT_JPG): {
                ImageIO.write(image, "jpg", new File(SavePath.DEFAULT_SAVE_PATH));
                break;
            }
            case (SupportFormat.FORMAT_PNG): {
                ImageIO.write(image, "png", new File(SavePath.DEFAULT_SAVE_PATH));
                break;
            }
            case (SupportFormat.FORMAT_GIF): {
                ImageIO.write(image, "gif", new File(SavePath.DEFAULT_SAVE_PATH));
                break;
            }
        }
    }
}
