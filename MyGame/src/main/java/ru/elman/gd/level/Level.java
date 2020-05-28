package ru.elman.gd.level;

import ru.elman.gd.graphics.Screen;

public abstract class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    private void loadLevel(String path){}

    private void update(){}

    private void render(int xScroll, int yScroll, Screen screen){}

    private void time(){}

    private void generateLevel(){}
}
