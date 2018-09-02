package PictureOperation;

/**
 * @author wmlbuaa
 * @date 2018-09-02 21:04
 */

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