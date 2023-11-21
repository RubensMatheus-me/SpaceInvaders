package ifpr.paranavai.game.models.shoots;

import ifpr.paranavai.game.models.GraphicElement;
import ifpr.paranavai.game.models.Player;

import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "tb_super_shoot")
public class SuperShoot extends GraphicElement {
    @ManyToOne
    @JoinColumn(name = "fk_player")
    private Player player;
    private static int SPEED = 10;
    @Column(name = "direction")
    private int direction;

    public SuperShoot(int positionX, int positionY, int direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        isVisible = true;
    }

    public SuperShoot() {
    }

    @Override
    public void load() {
        ImageIcon reference = new ImageIcon(getClass().getResource("/superShoot.png"));
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    @Override
    public void update() {
        //atirar nas diagonais
        this.positionY = (this.positionY - SPEED);

        if (direction == 1) {
            this.positionX -= SPEED; // Movimento diagonal superior esquerdo
        } else if (direction == 2) {
            this.positionX += SPEED; // Movimento diagonal superior direito
        } else if (direction == 3) {
            this.positionX -= SPEED; // Movimento horizontal para a esquerda
        } else if (direction == 4) {
            this.positionX += SPEED; // Movimento horizontal para a direita
        }

    }
    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        SuperShoot.SPEED = SPEED;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
