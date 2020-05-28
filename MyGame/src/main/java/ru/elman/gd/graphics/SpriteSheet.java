package ru.elman.gd.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private final int SIZE;
    private String path;

    public int[] pixels;

    public static SpriteSheet sheet = new SpriteSheet("/textures/canvas.png", 256);

    public SpriteSheet(String path, int size) {
        SIZE = size;
        this.path = path;
        pixels = new int[SIZE * SIZE];

        load();
    }

    public int getSIZE() {
        return SIZE;
    }

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
