package ifpr.paranavai.game.models;

import javax.swing.*;

public class Enemy1 extends GraphicElement {

    //private static final int WIDTH = 720;
    private static int SPEED = 3;
    public Enemy1(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        isVisible = true;
    }
    @Override
    public void load() {
        ImageIcon reference = new ImageIcon(getClass().getResource("/Mochtroid.png"));
        image = reference.getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }
    @Override
    public void update() {
        this.positionY += SPEED;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public static void setSPEED(int SPEED) {
        Enemy1.SPEED = SPEED;
    }
}
