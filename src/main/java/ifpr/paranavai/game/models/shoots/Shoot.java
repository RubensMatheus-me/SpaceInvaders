package ifpr.paranavai.game.models.shoots;

import ifpr.paranavai.game.models.GraphicElement;
import ifpr.paranavai.game.models.Player;

import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "tb_shoot")
public class Shoot extends GraphicElement {
    @ManyToOne
    @JoinColumn(name = "fk_player")
    private Player player;
    private static int SPEED = 10;


    public Shoot(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        isVisible = true;
    }

    public Shoot() {
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}