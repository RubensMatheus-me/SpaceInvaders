package ifpr.paranavai.game.game;

import Connection.HibernateUtil;
import ifpr.paranavai.game.models.Gamer;
import ifpr.paranavai.game.models.levels.Level;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;


public class Game extends JFrame {

    private static int WIDTH = 1280;
    private static int HEIGHT = 720;
    public Game() {
        setBackground(Color.BLACK);
        add(new Level());
        setTitle("Game by Rubao"); //nome da janela
        setSize(WIDTH, HEIGHT); //tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //parar de executar o programa quando fechar a janela;
        setLocationRelativeTo(null); // define onde a janela irá aparecer, como é nulo irá aparecer no meio da tela
        setResizable(false); //nao pode alterar o tamanho da janela, por isso false
        setVisible(true); // define a visibilidade da janela como visivel(true)

    }

    public static void main (String[] args) {
        Session session = HibernateUtil.getSession();
        //session.beginTransaction();
        //Gamer local = new Gamer("Joãozinho");
        //session.save(local);
        //session.getTransaction().commit();
        //HibernateUtil.encerraSession();
        new Game();
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        Game.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Game.HEIGHT = HEIGHT;
    }
}
