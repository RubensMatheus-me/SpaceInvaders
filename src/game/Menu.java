/*
package game;

import java.awt.*;

public class Menu {
    public String[] options = {"jogar", "carregar jogo", "opcoes", "sair"};
    public int currentOption = 0;
    public int maxOption = options.length - 1;
    public boolean up, down, enter;

    //
    public void tick() {
        if (down) {
            currentOption ++;
            down = false;
            if(currentOption > maxOption) {
                currentOption = 0; //ou = maxOption para nao voltar a primeira opcao
            }
        }
        if (up) {
            currentOption --;
            up = false;
            if(currentOption < 0) {
                currentOption = maxOption;
            }
        }
    }
    public void render (Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Game.WIDTH * Game.SCALE, );
    }

}
*/