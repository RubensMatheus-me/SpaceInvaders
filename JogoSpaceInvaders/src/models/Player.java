package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {
    private int positionX;
    private int positionY;
    private int deslocationX;

    private int deslocationY;
    private Image image;
    private int width;
    private int height;
    private int widthImage;
    private int heightImage;
    private ArrayList <Bullet> bullets;

    private static final int DESLOCATION = 4;
    private static final int INITIAL_DESLOCATIONX = 100;
    private static final int INITIAL_DESLOCATIONY = 100;

    public Player() {
        this.positionX = INITIAL_DESLOCATIONX;
        this.positionY = INITIAL_DESLOCATIONY;

        bullets = new ArrayList<Bullet>();
    }
    public void load() {
        ImageIcon loading = new ImageIcon("src/resources/Ship3.png");
        this.image = loading.getImage();
        this.heightImage = this.image.getHeight(null);
        this.widthImage = this.image.getWidth(null);
    }

    public void update() {
        positionX += deslocationX;
        positionY += deslocationY;
    }

    public void simpleBullets() {
        this.bullets.add(new Bullet(positionX + height, positionY + width / 2));
    }

    public void move(KeyEvent key) {
        int code = key.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                this.deslocationY = -DESLOCATION;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocationY = DESLOCATION;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocationX = -DESLOCATION;
                break;
            case KeyEvent.VK_RIGHT:
                this.deslocationX = DESLOCATION;
                break;
            default:
                break;
        }
    }

    public void stop(KeyEvent tecla) {
        int code = tecla.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                deslocationY = 0;
                break;
            case KeyEvent.VK_DOWN:
                deslocationY = 0;
                break;
            case KeyEvent.VK_LEFT:
                deslocationX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                deslocationX = 0;
                break;
            default:
                break;
        }
    }
    public void shoot(){
        int frontShip = this.positionX + this.width;
        int middleShip = this.positionY + (this.width / 2);
        Bullet bullet = new Bullet(frontShip, middleShip);
        this.bullets.add(bullet);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getDeslocationX() {
        return deslocationX;
    }

    public int getDeslocationY() {
        return deslocationY;
    }


    public Image getImage() {
        return image;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public int getWidthImage() {
        return widthImage;
    }


    public int getHeightImage() {
        return heightImage;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }


}
