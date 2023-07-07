package models;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bullet extends GraphicElement {
    private static final int WIDTH = 720;
    private static int SPEED = 5;

    public Bullet(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        isVisible = true;
    }

    public void load() {
        ImageIcon reference = new ImageIcon("src/resources/bullet.png");
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    @Override
    public void update() {
        this.positionY = this.positionY - SPEED;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public static void setSPEED(int SPEED) {
        Bullet.SPEED = SPEED;
    }
}