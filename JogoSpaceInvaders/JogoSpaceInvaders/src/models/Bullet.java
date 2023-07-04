package models;

import javax.swing.*;
import java.awt.Image;
public class Bullet {

    private Image image;
    private int x, y;
    private int height, wight;
    private boolean isVisible;

    private static final int HEIGHT = 938;
    private static int SPEED = 2;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }
    public void load() {
        ImageIcon reference = new ImageIcon("src/resources/bullet.png");
        image = reference.getImage();

        this.wight = image.getWidth(null);
        this.height = image.getWidth(null);
    }

    public void update() {
        this.x += SPEED;
            if(this.x > HEIGHT) {
                isVisible = false;
            }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public static void setSPEED(int SPEED) {
        Bullet.SPEED = SPEED;
    }
}
