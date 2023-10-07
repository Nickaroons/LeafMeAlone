package com.MatrixEngine;

import com.MatrixEngine.DataTypes.Vector2;
import com.MatrixEngine.Input.Input;
import com.MatrixEngine.Input.Mouse;
import com.MatrixEngine.DataTypes.Time;
import com.MatrixEngine.EngineFunctions.DataRegistry;
import com.MatrixEngine.Networking.UserClient;
import com.MatrixEngine.Scenes.Default;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

public class Engine extends Canvas implements Runnable {
    int HEIGHT = 500, WIDTH = 500;
    public String title = "Matrix com.MatrixEngine.Engine";
    private Thread thread;
    private boolean isRunning = false;
    private int x = 0;
    public Input input = new Input();
    public long lastSecond = System.nanoTime();
    public static long FPS = 0;
    public static long currentFPS = 0;
    public static Camera camera;
    public static int tileSize = 10;
    //public static UserClient userClient;

    public Engine() {
        new Window(HEIGHT, WIDTH, title, this);
        start();
        init();
    }

    public static void close() {
        // Called when game closes
        DataRegistry.save( "Data/", "data");
    }

    private void init() {
        this.addKeyListener(input);
        this.addMouseListener(new Mouse());
        //DataRegistry.addData(new SaveData("name", "coolest_data_ever"));
        DataRegistry.load("Data/", "data");
        System.out.println("Loaded Data: " + DataRegistry.getData("name"));
        camera = new Camera(100, 0);
    }

    @Override
    public void run() {
        this.requestFocus();
        SpriteLoader.load();
        SceneManager.loadScene(new Default());
        while (isRunning) {
            Time.deltaTime = ((System.nanoTime() - Time.lastUpdateTime)/1000000000.0);
            Time.lastUpdateTime = System.nanoTime();
            render();
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // render graphics
        SceneManager.update();
        camera.update();
        ObjectHandler.updateThenRender(g);
        UIRegistry.updateThenRender(g);
        bs.show();
        g.dispose();
        FPS++;
        if (System.nanoTime() - lastSecond > 1000000000.0) {
            lastSecond = System.nanoTime();
            currentFPS = FPS;
            FPS = 0;
        }
    }

    private synchronized void start() {
        if (isRunning) return;
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    private synchronized void stop() {
        if (!isRunning) return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }



    // engine Main Class
    public static void main (String[] args) {
        new Engine();
    }


    // Engine Methods
    public static int randInt(int lower, int upper) {
        return ThreadLocalRandom.current().nextInt(lower, upper);
    }

    public static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    public static void playSound(String soundFile) {
        try {
            URL soundURL = Engine.class.getResource("/Sounds/" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    // val of 1 = same volume, val of < 1 quieter, val of > 1 louder (ex val of 2 = 2x volume)
    public static void playSound(String soundFile, float volume) {
        try {
            URL soundURL = Engine.class.getResource("/Sounds/" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            clip.start();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSoundLoop(String soundFile, float volume) {
        try {
            URL soundURL = Engine.class.getResource("/Sounds/" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    public static Vector2 relativePosition(int x, int y) {
       return new Vector2(x-camera.getPosition().x, y-camera.getPosition().y);
    }
    public static Vector2 relativePosition(Vector2 position) {
        return new Vector2(position.getX() - camera.getPosition().x, position.getY() - camera.getPosition().y);
    }
}
