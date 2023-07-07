package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Level extends JPanel implements ActionListener, KeyListener {

    private Image background;
    private Player player;
    private Timer timer;
    protected ArrayList<ImageIcon> lifes = new ArrayList<>();
    protected boolean inGame;
    private int decay = 2;
    private Enemy1 targetEnemy;

    private static final int DELAY = 5;
    private static final int HEIGHT_WINDOW = 1280;
    protected List<Enemy1> enemy1 = new ArrayList<Enemy1>();


    public Level() {
        setFocusable(true);
        setDoubleBuffered(true);

        //adicionando a vida ao arraylist
        lifes.add(new ImageIcon("src/resources/vida.png"));
        lifes.add(new ImageIcon("src/resources/vida.png"));
        lifes.add(new ImageIcon("src/resources/vida.png"));

        ImageIcon loader = new ImageIcon("src/resources/maxresdefault.jpg");
        background = loader.getImage();
        player = new Player();
        player.load();

        addKeyListener(this);

        timer = new Timer(DELAY, this);
        timer.start();

        initializeEnemyes();
        inGame = true;

        Timer enemySpawn = new Timer(2000, newActionLister);
        enemySpawn.start();
    }
    public void initializeEnemyes() {
        int coord[] = new int [4]; //colocando 40 inimigos
        Rectangle rec;
        boolean overlap;

        for (int i = 0; i < coord.length; i++) {
            overlap = false;
            int x = (int)(Math.random() * 1280); //OITO MIL???
            int y = (int)(Math.random() * -200 - 150); // altura
            rec = new Rectangle(x, y, 50, 50);

            //método para os inimigos nao nascerem um em cima do outro
            for(int j = 0;j<enemy1.size();j++) {
                if(enemy1.get(j).getBounds().intersects(rec)) {
                    System.out.println("BOUNDS" + overlap);
                    overlap = true;
                    System.out.println("BOUNDS" + overlap);
                }
            }
            if(!overlap) {
                enemy1.add(new Enemy1(x, y));
            }

        }
    }
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if (inGame == true) {
            graphics.drawImage(background, 0, 0, null);
            graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);

            for(int i = 0; i < lifes.size(); i++) {
                graphics.drawImage(lifes.get(i).getImage(), 50 * i, 0, null);
            }


            ArrayList<Shoot> shoots = player.getShoots();
            for (Shoot shoot : shoots) {
                shoot.load();

                graphics.drawImage(shoot.getImage(), shoot.getPositionX(), shoot.getPositionY(), this);
            }

            ArrayList<SuperShoot> superShoots = player.getSuperShoots();
            for (SuperShoot superShoot : superShoots) {
                superShoot.load();

                graphics.drawImage(superShoot.getImage(), superShoot.getPositionX(), superShoot.getPositionY(), this);
            }

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 in = enemy1.get(o);
                in.load();
                graphics.drawImage(in.getImage(), in.getPositionX(), in.getPositionY(), this);
            }
        }

        else {
            ImageIcon gameOver = new ImageIcon("src/resources/gamerover.png");
            graphics.drawImage(gameOver.getImage(), 400, 100, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        ArrayList<SuperShoot> superShoots = player.getSuperShoots();
        for (int j = 0; j < superShoots.size(); j++) {

            //removendo os supertiros da tela
            if (superShoots.get(j).getPositionY() < 0) {
                superShoots.remove(j);
                System.out.println("tiro removido");
            }
            else {
                superShoots.get(j).update();
            }

        }

        ArrayList<Shoot> shoots = player.getShoots();
        //removendo os tiros da tela
        for (int i = 0; i < shoots.size(); i++) {
            if (shoots.get(i).getPositionY() < 0) {
                shoots.remove(i);
                System.out.println("tiro removido");
            }
            else {
                shoots.get(i).update();
            }

        }
        for (int o = 0; o < enemy1.size(); o++) {
            Enemy1 in = enemy1.get(o);
            if (in.isVisible()) {
                in.update();
            }else {
                enemy1.remove(o);
                System.out.println("inimigo morto");

            }
        }
        checkCollision();
        repaint();
    }

    public void checkCollision() {
        Rectangle formShip = player.getBounds();
        Rectangle formShoot;
        Rectangle formEnemy1;
        Rectangle formSuperShoot;

        //colisão da nave com o inimigo
        for (int i = 0; i < enemy1.size(); i++) {
            Enemy1 tempEnemy1 = enemy1.get(i);
            formEnemy1 = tempEnemy1.getBounds();

            if (formShip.intersects(formEnemy1)) {
                player.setVisible(false);
                tempEnemy1.setVisible(false);
                enemy1.remove(i);

                //definindo a troca de imagem quando a nave perde vida
                ImageIcon image = new ImageIcon("src/resources/semVida.png");
                lifes.get(decay).setImage(image.getImage());
                decay -= 1;

                //definindo a queda da vida a cada colisao com o inimigo
                player.life -= 1;
                if (player.life == 0 || lifes.size() == 0) {
                    inGame = false;
                }
            }

        }
        //colisao do tiro com o inimigo
        List<Shoot> shoots = player.getShoots();
        for (int j = 0; j < shoots.size(); j++) {
            Shoot tempShoot = shoots.get(j);
            formShoot = tempShoot.getBounds();

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 tempEnemy1 = enemy1.get(o);
                formEnemy1 = tempEnemy1.getBounds();

                if (formShoot.intersects(formEnemy1)) {
                    tempEnemy1.setVisible(false);
                    tempShoot.setVisible(false);
                    shoots.remove(j);
                }
            }
        }
        //colisao do supertiro com o inimigo
        List<SuperShoot> superShoots = player.getSuperShoots();
        for (int j = 0; j < superShoots.size(); j++) {
            SuperShoot tempSuperShoot = superShoots.get(j);
            formSuperShoot = tempSuperShoot.getBounds();

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 tempEnemy1 = enemy1.get(o);
                formEnemy1 = tempEnemy1.getBounds();

                if (formSuperShoot.intersects(formEnemy1)) {
                    tempEnemy1.setVisible(false);
                    tempSuperShoot.setVisible(false);
                    superShoots.remove(j);
                }

                }
            }
        }



    ActionListener newActionLister = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeEnemyes();

        }
    };
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.move(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shoot();

        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            player.superShoot();
        }
        player.stop(e);
    }
}
