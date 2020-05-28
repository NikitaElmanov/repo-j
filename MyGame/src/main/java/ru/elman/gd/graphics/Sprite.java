package ru.elman.gd.graphics;

public class Sprite {

    private final int SIZE;
    private int x, y;
    private SpriteSheet spriteSheet;

    public int[] pixels;

    public int getSIZE() {
        return SIZE;
    }

    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.sheet);

    public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.spriteSheet = spriteSheet;

        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++){
            for (int x = 0; x < SIZE; x++){
                pixels[x + y * SIZE] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.getSIZE()];
            }
        }
    }
}
