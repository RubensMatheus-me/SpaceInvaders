package ifpr.paranavai.game.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;

@Entity
@Table(name = "tb_shoot")
public class Shoot extends GraphicElement {
    private static final int WIDTH = 720;
    private static int SPEED = 10;

    public Shoot(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        isVisible = true;
    }

    public void load() {
        ImageIcon reference = new ImageIcon(getClass().getResource("/bullet.png"));
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
        Shoot.SPEED = SPEED;
    }
}