package UI;

import Ashing.Ashing;
import PictureOperation.Picture;
import PictureOperation.PixelStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author wmlbuaa
 * @date 2018-08-26
 */
public class ConsoleMain {
    public static void main(String[] args) {
        Picture picture = new Picture();
        try {
            BufferedImage image = ImageIO.read(new File("Picture//source_image.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ashing ashing =new Ashing(picture);
    }
}