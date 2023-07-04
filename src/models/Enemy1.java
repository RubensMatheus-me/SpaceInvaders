package models;

import javax.swing.*;
import java.awt.*;

public class Enemy1 {

    private Image image;
    private int x, y;
    private int height, wight;
    private boolean isVisible;

    //private static final int WIDTH = 720;
    private static int SPEED = 5;

    public Enemy1(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load() {
        ImageIcon reference = new ImageIcon("src/resources/Mochtroid.png");
        image = reference.getImage();

        this.wight = image.getWidth(null);
        this.height = image.getWidth(null);
    }

    public void update() {
        this.y += SPEED;
        //if (this.getX() > WIDTH) {

            return;
        //}

    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, wight, height);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
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
}
