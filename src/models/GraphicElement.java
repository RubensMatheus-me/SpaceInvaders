package models;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class GraphicElement {
    protected int positionX;
    protected int positionY;
    protected int deslocationX;
    protected int deslocationY;
    protected Image image;
    protected int height;
    protected int width;
    protected int widthImage;
    protected int heightImage;
    protected boolean isVisible;

    public GraphicElement() {
        isVisible = true;
    }
    public abstract void load();

    public abstract void update();

    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, width, height);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getDeslocationX() {
        return deslocationX;
    }

    public void setDeslocationX(int deslocationX) {
        this.deslocationX = deslocationX;
    }

    public int getDeslocationY() {
        return deslocationY;
    }

    public void setDeslocationY(int deslocationY) {
        this.deslocationY = deslocationY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidht() {
        return width;
    }

    public void setWidht(int widht) {
        this.width = widht;
    }

    public  boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidthImage() {
        return widthImage;
    }

    public void setWidthImage(int widthImage) {
        this.widthImage = widthImage;
    }

    public int getHeightImage() {
        return heightImage;
    }

    public void setHeightImage(int heightImage) {
        this.heightImage = heightImage;
    }
}
