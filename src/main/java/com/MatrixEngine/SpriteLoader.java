package com.MatrixEngine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;

public class SpriteLoader {
    public static BufferedImage player;
    public static Hashtable<String, BufferedImage> pathDirectory = new Hashtable<String, BufferedImage>();
    public static void load() {
        try {
            player = load("player.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static BufferedImage load(String path) throws IOException {
        BufferedImage img = ImageIO.read(SpriteLoader.class.getResource("/" + path));
        pathDirectory.put(path, img);
        return img;
    }

    public static String getBufferedImagePath(BufferedImage img) {
        for(Map.Entry entry: pathDirectory.entrySet()){
            if(img.equals(entry.getValue())){
                return String.valueOf(entry.getKey());
            }
        }
        return null;
    }
}
