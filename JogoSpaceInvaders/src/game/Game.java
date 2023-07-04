package game;

import models.Level;

import javax.swing.*;


public class Game extends JFrame {

    public Game() {
        add(new Level());
        setTitle("Game by Rubao");//nome da janela
        setSize(1280, 720);//tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//parar de executar o programa quando fechar a janela;
        setLocationRelativeTo(null);// define onde a janela irá aparecer, como é nulo irá aparecer no meio da tela
        setResizable(false); //nao pode alterar o tamanho da janela, por isso false
        setVisible(true); // define a visibilidade da janela como visivel(true)

    }


}
