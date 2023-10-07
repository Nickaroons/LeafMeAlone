package com.MatrixEngine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {
    public static BufferedImage rock, potato;
    public static void load() {
        try {
            rock = load("rock.png");
            potato = load("potatoIcon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static BufferedImage load(String path) throws IOException {
        return ImageIO.read(SpriteLoader.class.getResource("/" + path));
    }
}
