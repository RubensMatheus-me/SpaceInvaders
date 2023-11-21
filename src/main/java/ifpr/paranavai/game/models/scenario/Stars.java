package ifpr.paranavai.game.models.scenario;

import ifpr.paranavai.game.models.GraphicElement;
import ifpr.paranavai.game.view.Level;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.*;
import java.util.Random;

@Entity
@Table(name = "tb_stars")
public class Stars extends GraphicElement {
        //private static final int WIDTH = 720;
        @ManyToOne
        @JoinColumn(name = "fk_level")
        private Level level;
        private static int SPEED = 5;
        public Stars(int positionX, int positionY) {
            this.positionX = positionX;
            this.positionY = positionY;
            isVisible = true;
        }

    public Stars() {}

    @Override
        public void load() {
            ImageIcon reference = new ImageIcon(getClass().getResource("/star.png"));
            image = reference.getImage();
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }
        @Override
        public void update() {
            if(this.positionY > 720) {
                this.positionY = height;
                Random randomS = new Random();
                int m = randomS.nextInt(500);
                this.positionY = m - 1000;
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
            Stars.SPEED = SPEED;
        }
}