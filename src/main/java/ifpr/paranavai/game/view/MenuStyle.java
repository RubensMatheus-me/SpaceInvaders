package ifpr.paranavai.game.view;

import ifpr.paranavai.game.game.Game;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;


public class MenuStyle {
    Level lv;
    Graphics2D g2;
    Font maruMonica, purisaB;
    public int tileSize = Game.getHEIGHT() / 9;
    public int commandNum = 0;
    ImageIcon reference;


    public MenuStyle(Level lv) {

        reference = new ImageIcon(getClass().getResource("/Mochtroid.png"));
        this.lv = lv;
        try {
            InputStream is = getClass().getResourceAsStream("/maruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/purisaBold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (lv.gameState == lv.playState) {
            //
        }
        if (lv.gameState == lv.pauseState) {
            drawPauseScreen();
        }

        if (lv.gameState == lv.menuState) {
            drawMenuScreen();
        }
    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = Game.getHEIGHT() / 2;
    }
    public void drawMenuScreen() {
        if (lv.gameState == lv.menuState) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, Game.getWIDTH(), Game.getHEIGHT());

            //nome do jogo
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "JOGO RUBAO";
            int x = getXforCenteredText(text);
            int y = Game.getHEIGHT() / 6;

            //sombras
            g2.setColor(Color.gray);
            g2.drawString(text, x+ 5, y + 5);

            //cor da fonte
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //logo
            x = Game.getWIDTH() / 2 - (Game.getHEIGHT()/ 14);
            y += (Game.getHEIGHT()/ 2) / 5;
            g2.drawImage(reference.getImage(), x, y,Game.getHEIGHT()/ 8,Game.getWIDTH() / 8, null);

            //menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", (x - tileSize) + 35, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", (x - tileSize) + 35, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += tileSize ;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", (x - tileSize) + 35, y);
            }
        }

    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = Game.getWIDTH() / 2 - length / 2;
        return x;
    }
    public void menuLogic(KeyEvent key) {
        int code = key.getKeyCode();
        if (lv.gameState == lv.menuState) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                commandNum--;
                if (commandNum < 0) {
                    commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                commandNum++;
                if (commandNum > 2) {
                    commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (commandNum == 0) {
                    lv.gameState = lv.playState;
                    lv.setInGame(true);
                    lv.setupGame();
                }
                if (commandNum == 1) {
                    lv.loadGame();
                    lv.setInGame(true);
                    lv.setupGame();
                    lv.gameState = lv.playState;
                    System.out.println("ai");
                }
                if (commandNum == 2) {
                    System.exit(0);
                }
                if (code == KeyEvent.VK_P) {
                    if (lv.gameState == lv.playState) {
                        lv.gameState = lv.pauseState;
                        System.out.println("333333333333");
                    } else if (lv.gameState == lv.pauseState) {
                        lv.gameState = lv.playState;
                        System.out.println("333333333333");
                    }

                }

            }
        }
    }
    public void saveLogic(KeyEvent key) {
        int code = key.getKeyCode();
        if (lv.gameState == lv.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                lv.gameState = lv.menuState;
                lv.setInGame(false);
                new Game();

                //new Level();
            }
            if (code == KeyEvent.VK_F5) {
                lv.deleteGame();
                lv.saveGame();
            }
        }
    }
}
