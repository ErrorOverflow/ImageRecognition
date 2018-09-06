package UI;

import PictureOperation.PixelStream;

/**
 * @author wmlbuaa
 * @date 2018-08-26
 */
public class ConsoleMain {
    public static void main(String[] args) {
        PixelStream a = new PixelStream("C:\\Users\\WML\\IdeaProjects\\ImageRecognition\\Picture\\source_image.jpg");
        a.run();
    }
}