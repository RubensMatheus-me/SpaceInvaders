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
    private boolean isVisible;

    private static final int DESLOCATION = 4;
    private static final int INITIAL_DESLOCATIONX = 100;
    private static final int INITIAL_DESLOCATIONY = 100;

    public Player() {
        this.positionX = INITIAL_DESLOCATIONX;
        this.positionY = INITIAL_DESLOCATIONY;
        isVisible = true;

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

    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, widthImage, heightImage);
    }

    //movimentar ao apertar a tecla
    public void move(KeyEvent key) {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            this.deslocationY = -DESLOCATION;
        }else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            this.deslocationY = DESLOCATION;
        }else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            this.deslocationX = -DESLOCATION;
        }else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            this.deslocationX = DESLOCATION;
        }
    }
    //parar de movimentar quando soltar a tecla
    public void stop(KeyEvent key) {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            deslocationY = 0;
        }else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            deslocationY = 0;
        }else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            deslocationX = 0;
        }else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            deslocationX = 0;
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
