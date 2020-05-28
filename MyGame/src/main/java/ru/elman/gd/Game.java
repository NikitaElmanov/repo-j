package ru.elman.gd;

import ru.elman.gd.graphics.Screen;
import ru.elman.gd.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Objects;

public class Game extends Canvas implements Runnable{
    public static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = 168;
    public static int scale = 3;
    public static String title = "My Game";

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Screen screen;

    private boolean running = false;


    private BufferedImage image = new BufferedImage(width * scale, height * scale, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {

        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width * scale, height * scale);

        frame = new JFrame();

        key = new Keyboard();

        addKeyListener(key);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000_000_000.0d / 60.0d;
        double delta = 0;
        int updates = 0, frames = 0;
        requestFocus();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
    }

    public void update(){
        key.update();
        if (key.isUp()) y--;
        if (key.isDown()) y++;
        if (key.isLeft()) x--;
        if (key.isRight()) x++;
    }

    int x = 0, y = 0;

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (Objects.isNull(bs)){
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        screen.render(x,y);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();//checking
        bs.show();

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
