package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends GraphicElement {
    private ArrayList<Bullet> bullets;

    private static final int DESLOCATION = 5;
    private static final int INITIAL_DESLOCATIONX = 550;
    private static final int INITIAL_DESLOCATIONY = 550;
    protected static int LIFE = 3;

    public Player() {
        this.positionX = INITIAL_DESLOCATIONX;
        this.positionY = INITIAL_DESLOCATIONY;
        isVisible = true;

        bullets = new ArrayList<Bullet>();
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon("src/resources/Ship3.png");
        this.image = loading.getImage();
        this.height = this.image.getHeight(null);
        this.width = this.image.getWidth(null);
    }
    @Override
    public void update() {
        positionX += deslocationX;
        positionY += deslocationY;
    }

    public void simpleBullets() {
        this.bullets.add(new Bullet(positionX + height, positionY + width / 2));
    }

    //movimentar ao apertar a tecla
    public void move(KeyEvent key) {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            this.deslocationY = -DESLOCATION;
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            this.deslocationY = DESLOCATION;
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            this.deslocationX = -DESLOCATION;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            this.deslocationX = DESLOCATION;
        }
    }

    //parar de movimentar quando soltar a tecla
    public void stop(KeyEvent key) {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            deslocationY = 0;
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            deslocationY = 0;
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            deslocationX = 0;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            deslocationX = 0;
        }
    }

    public void shoot() {
        int frontShip = this.positionX + this.widthImage;
        int middleShip = this.positionY + (this.widthImage / 2);
        Bullet bullet = new Bullet(frontShip, middleShip);
        this.bullets.add(bullet);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}