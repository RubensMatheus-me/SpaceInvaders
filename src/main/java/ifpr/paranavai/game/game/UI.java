package ifpr.paranavai.game.game;

import ifpr.paranavai.game.models.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    Level lv;
    Graphics2D g2;
    Font maruMonica, purisaB;
    int menuSize = 1;
    ImageIcon reference;


    public UI (Level lv) {

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

        if (lv.gameState == lv.menuState) {
            drawMenuScreen();
        }
    }
    public void drawMenuScreen() {

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, Game.getWIDTH(), Game.getHEIGHT());

        //nome do jogo
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Jogo rubao";
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
        y += (Game.getHEIGHT() / 5) * 2;
        g2.drawString(text, x, y);

        //arrumar
        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += Game.getHEIGHT();
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = Game.getWIDTH() / 2 - length / 2;
        return x;
    }
}
