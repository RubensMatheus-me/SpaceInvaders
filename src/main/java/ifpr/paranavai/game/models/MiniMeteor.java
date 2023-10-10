package ifpr.paranavai.game.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;
import java.util.Random;
@Entity
@Table(name = "tb_mini_meteor")
public class MiniMeteor extends GraphicElement{
    //private static final int WIDTH = 720;
    private static int SPEED = 2;
    public MiniMeteor(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        isVisible = true;
    }
    @Override
    public void load() {
        ImageIcon reference = new ImageIcon(getClass().getResource("/miniMeteor.png"));
        image = reference.getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }
    @Override
    public void update() {
        if(this.positionY > 1280) {
            this.positionY = height;
            Random randomS = new Random();
            int m = randomS.nextInt(500);
            this.positionY = m + 720;
            Random r = new Random();
            int n = r.nextInt(1280);
            this.positionX = n;

        } else {
            this.positionY += SPEED;
        }
    }

    public static int getSPEED() {
        return SPEED;
    }

    public static void setSPEED(int SPEED) {
        MiniMeteor.SPEED = SPEED;
    }
}
