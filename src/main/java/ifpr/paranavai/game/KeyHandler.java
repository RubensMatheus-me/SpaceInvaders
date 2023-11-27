package ifpr.paranavai.game;

import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.enemies.Enemy1;
import ifpr.paranavai.game.view.Level;
import ifpr.paranavai.game.view.MenuStyle;

import java.awt.event.KeyEvent;

public class KeyHandler {
    Enemy1 enemy;
    Level lv;
    Player player;
    MenuStyle ms;

    public KeyHandler(Level lv) {
        this.lv = lv;
    }
    public void menuLogic(KeyEvent key) {
        int code = key.getKeyCode();
        if (lv.gameState == lv.menuState) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                ms.commandNum--;
                if (ms.commandNum < 0) {
                    ms.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                ms.commandNum++;
                if (ms.commandNum > 2) {
                    ms.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (ms.commandNum == 0) {
                    lv.gameState = lv.playState;
                    lv.setInGame(true);
                    lv.setupGame();
                }
                if (ms.commandNum == 1) {
                    lv.loadGame();
                    lv.setInGame(true);
                    lv.setupGame();
                    lv.gameState = lv.playState;
                    System.out.println("ai");
                }
                if (ms.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
    }

    public Enemy1 getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy1 enemy) {
        this.enemy = enemy;
    }

    public Level getLv() {
        return lv;
    }

    public void setLv(Level lv) {
        this.lv = lv;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MenuStyle getMs() {
        return ms;
    }

    public void setMs(MenuStyle ms) {
        this.ms = ms;
    }
}
