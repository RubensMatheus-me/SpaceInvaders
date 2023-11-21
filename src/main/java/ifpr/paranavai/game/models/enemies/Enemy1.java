package ifpr.paranavai.game.models.enemies;

import ifpr.paranavai.game.models.GraphicElement;
import ifpr.paranavai.game.view.Level;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.*;

@Entity
@Table(name = "tb_enemy1")
public class Enemy1 extends GraphicElement {
    @ManyToOne
    @JoinColumn(name = "fk_level")
    private Level level;

    //private static final int WIDTH = 720;
    private static int SPEED = 3;
    public Enemy1(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        isVisible = true;
    }

    public Enemy1() {
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
