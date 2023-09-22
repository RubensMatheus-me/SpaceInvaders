package ifpr.paranavai.game.models;

import javax.swing.*;
import java.util.Random;

public class Stars extends GraphicElement{
        //private static final int WIDTH = 720;
        private static int SPEED = 5;
        public Stars(int positionX, int positionY) {
            this.positionX = positionX;
            this.positionY = positionY;
            isVisible = true;
        }
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