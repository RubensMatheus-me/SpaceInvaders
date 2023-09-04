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
    protected ArrayList<ImageIcon> lifes = new ArrayList<>(4);
    protected boolean inGame;
    private int decay = 2;
    private Enemy1 targetEnemy;

    private static final int DELAY = 5;
    private static final int HEIGHT_WINDOW = 1280;
    private static final int SCORE_FOR_ENEMIES = 10;
    protected List<Enemy1> enemy1 = new ArrayList<Enemy1>();
    protected List<MiniMeteor> miniMeteors = new ArrayList<MiniMeteor>();
    protected List<Stars> stars = new ArrayList<Stars>();


    public Level() {
        setFocusable(true);
        setDoubleBuffered(true);

        //adicionando a vida ao arraylist
        lifes.add(new ImageIcon("src/resources/vida.png"));
        lifes.add(new ImageIcon("src/resources/vida.png"));
        lifes.add(new ImageIcon("src/resources/vida.png"));

        ImageIcon loader = new ImageIcon("src/resources/new background.png");
        background = loader.getImage();
        player = new Player();
        player.load();

        addKeyListener(this);

        timer = new Timer(DELAY, this);
        timer.start();

        initializeEnemies();
        initializeMiniMeteors();
        initializeStars();
        inGame = true;

        Timer enemySpawn = new Timer(2000, newActionLister);
        enemySpawn.start();
    }
    public void initializeEnemies() {
        int coord[] = new int [4]; //colocando 4 inimigos de uma vez
        Rectangle rec;
        boolean overlap;

        for (int i = 0; i < coord.length; i++) {
            overlap = false;
            int x = (int)(Math.random() * 1280); //
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
    public void initializeMiniMeteors() {
        int coord[] = new int[2];
        Rectangle recMeteor;
        for (int i = 0; i < coord.length; i++) {
            int x = (int) (Math.random() * 1280); //
            int y = (int) (Math.random() * -1000); // altura
            miniMeteors.add(new MiniMeteor(x, y));
            recMeteor = new Rectangle(x, y, 50, 50);
        }
    }
    public void initializeStars() {
        int coord[] = new int[50];
        for (int i = 0; i < coord.length; i++) {
            int x = (int) (Math.random() * 1280); //
            int y = (int) (Math.random() * -1280); // altura
            stars.add(new Stars(x, y));
        }
    }
    public void drawningScore(Graphics2D g) {
        String textScore = "SCORE: " + player.getScore();
        g.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        g.setColor(new java.awt.Color(255, 255, 255));
        g.drawString(textScore, 1130, 30); // x, y
    }
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if (inGame == true) {
            graphics.drawImage(background, 0, 0, null);
            drawningScore(graphics);


            for (int z = 0; z < miniMeteors.size(); z++) {
                MiniMeteor s = miniMeteors.get(z);
                s.load();
                graphics.drawImage(s.getImage(), s.getPositionX(), s.getPositionY(), this);
            }


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

            for (int j = 0; j < stars.size(); j++) {
                Stars star = stars.get(j);
                star.load();
                graphics.drawImage(star.getImage(), star.getPositionX(), star.getPositionY(), this);
            }
        }


        else {
            ImageIcon gameOver = new ImageIcon("src/resources/gameover.png");
            graphics.drawImage(gameOver.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        for (int z = 0; z < miniMeteors.size(); z++) {
            MiniMeteor on = miniMeteors.get(z);
            if(on.isVisible()) {
                on.update();
            } else {
                miniMeteors.remove(z);
            }
        }

        for (int s = 0; s < stars.size(); s++) {
            Stars on = stars.get(s);
            if(on.isVisible()) {
                on.update();
            } else {
                stars.remove(s);
            }
        }

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
        //removendo o inimigo quando passar da tela
        for (int i = 0; i < enemy1.size(); i++) {
            if (enemy1.get(i).getPositionY() > 720) {
                enemy1.remove(i);
                System.out.println("inimigo removido");
            }
            else {
                enemy1.get(i).update();
            }
        }
        //removendo o mini meteoro quando passar da tela
        for (int i = 0; i < miniMeteors.size(); i++) {
            if (miniMeteors.get(i).getPositionY() > 720) {
                miniMeteors.remove(i);
                System.out.println("mini meteoro removido");
            }
            else {
                miniMeteors.get(i).update();
            }
        }
        //removendo as estrelas quando passar da tela
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).getPositionY() > 720) {
                stars.remove(i);
                System.out.println("esterela removida");
            }
            else {
                stars.get(i).update();
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
        Rectangle formMiniMeteor;

        //colisão da nave com o inimigo
        for (int i = 0; i < enemy1.size(); i++) {
            Enemy1 tempEnemy1 = enemy1.get(i);
            formEnemy1 = tempEnemy1.getBounds();

            if (formShip.intersects(formEnemy1)) {
                int initialScore = this.player.getScore();
                this.player.setScore(initialScore + SCORE_FOR_ENEMIES);
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

        for (int i = 0; i < miniMeteors.size(); i++) {
            MiniMeteor tempStars = miniMeteors.get(i);
            formMiniMeteor = tempStars.getBounds();

            if (formShip.intersects(formMiniMeteor)) {
                int initialScore = this.player.getScore();
                this.player.setScore(initialScore + SCORE_FOR_ENEMIES);
                player.setVisible(false);
                tempStars.setVisible(false);
                miniMeteors.remove(i);

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
                    int initialScore = this.player.getScore();
                    this.player.setScore(initialScore + SCORE_FOR_ENEMIES);
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
                    int initialScore = this.player.getScore();
                    this.player.setScore(initialScore + SCORE_FOR_ENEMIES);
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
            initializeEnemies();
            initializeMiniMeteors();
            initializeStars();

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
