package PictureOperation;

/**
 * @author wmlbuaa
 * @date 2018-09-02 21:05
 */

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