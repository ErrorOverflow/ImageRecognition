package base;

public class EuclideanMetric {
    private int pixel_a;
    private int pixel_b;

    public EuclideanMetric(int color_a, int color_b) {
        this.pixel_a = color_a;
        this.pixel_b = color_b;
    }

    public double calculate() {
        int r_a = (pixel_a >> 16) & 0xff;
        int g_a = (pixel_a >> 8) & 0xff;
        int b_a = pixel_a & 0xff;
        int r_b = (pixel_b >> 16) & 0xff;
        int g_b = (pixel_b >> 8) & 0xff;
        int b_b = pixel_b & 0xff;
        return Math.sqrt(Math.pow(r_a - r_b, 2) + Math.pow(g_a - g_b, 2) + Math.pow(b_a - b_b, 2));
    }
}
