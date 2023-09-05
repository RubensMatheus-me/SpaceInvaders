package game;

import models.Level;

import javax.swing.*;


public class Game extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    public Game() {
        add(new Level());
        setTitle("Game by Rubao"); //nome da janela
        setSize(WIDTH, HEIGHT); //tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //parar de executar o programa quando fechar a janela;
        setLocationRelativeTo(null); // define onde a janela irá aparecer, como é nulo irá aparecer no meio da tela
        setResizable(false); //nao pode alterar o tamanho da janela, por isso false
        setVisible(true); // define a visibilidade da janela como visivel(true)

    }
    public static void main (String[] args) {
        new Game();
    }

}
