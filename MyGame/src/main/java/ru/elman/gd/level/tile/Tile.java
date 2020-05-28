package ru.elman.gd.level.tile;

import ru.elman.gd.graphics.Screen;
import ru.elman.gd.graphics.Sprite;

public abstract class Tile {

    private int x, y;
    private Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){
    }

    public boolean solid(){
        return false;
    }
}
