package models;

import org.w3c.dom.css.Rect;

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

    private static final int DELAY = 5;
    private static final int HEIGHT_WINDOW = 1280;
    protected List<Enemy1> enemy1 = new ArrayList<Enemy1>();


    public Level() {
        setFocusable(true);
        setDoubleBuffered(true);

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


//        for (int i = 0; i < coord.length; i++) {
//            System.out.println("AAA");
//            Rectangle rec = new Rectangle(0, 0, 0, 0);
//            int x, y;
//            do {
//                System.out.println("BBB");
//                x = (int)(Math.random() * -8000 + 1280); //OITO MIL???
//                y = (int)(Math.random() * -3000 + 35); // altura
//                rec.setBounds(x, y, 50, 50);
//            } while(enemy1.stream().allMatch(e -> e.getBounds().intersects(rec)));
//            enemy1.add(new Enemy1(x, y));
//        }


    }


    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if (inGame == true) {
            graphics.drawImage(background, 0, 0, null);
            graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);

            for(ImageIcon life : lifes) {
               graphics.drawImage(life.getImage(),0 ,0,null);
               graphics.drawImage(life.getImage(),50 ,0,null);
                graphics.drawImage(life.getImage(),100 ,0,null);
            }


            ArrayList<Bullet> bullets = player.getBullets();
            for (Bullet bullet : bullets) {
                bullet.load();

                graphics.drawImage(bullet.getImage(), bullet.getPositionX(), bullet.getPositionY(), this);
            }

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 in = enemy1.get(o);
                in.load();
                graphics.drawImage(in.getImage(), in.getPositionX(), in.getPositionY(), this);
            }
        }
        else {
            ImageIcon gameOver = new ImageIcon("src/resources/gamerover.png");
            graphics.drawImage(gameOver.getImage(), 450, 100, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        ArrayList<Bullet> bullets = player.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getPositionY() > HEIGHT_WINDOW) {
                bullets.remove(i);
            }
            else {
                bullets.get(i).update();
            }
        }
        for (int o = 0; o < enemy1.size(); o++) {
            Enemy1 in = enemy1.get(o);
            if (in.isVisible()) {
                in.update();
            }else {
                enemy1.remove(o);

            }
        }
        checkCollision();
        repaint();
    }

    public void checkCollision() {
        Rectangle formShip = player.getBounds();
        Rectangle formShoot;
        Rectangle formEnemy1;

        for (int i = 0; i < enemy1.size(); i++) {
            Enemy1 tempEnemy1 = enemy1.get(i);
            formEnemy1 = tempEnemy1.getBounds();
            if (formShip.intersects(formEnemy1)) {
                player.setVisible(false);
                tempEnemy1.setVisible(false);
                enemy1.remove(i);

                //ImageIcon image = new ImageIcon("src/resources/semVida.png");
                //lifes.get(1).setImage(image.getImage());
                //ImageIcon image = new ImageIcon("src/resources/semVida.png");
                //lifes.get(0).setImage(image.getImage());
                lifes.remove(2);
                decay -=1;
                //definindo a queda da vida a cada colisao com o inimigo
                player.LIFE -= 1;
                if (player.LIFE == 0) {
                    inGame = false;
                }
            }

        }

        List<Bullet> bullets = player.getBullets();
        for (int j = 0; j < bullets.size(); j++) {
            Bullet tempBullet = bullets.get(j);
            formShoot = tempBullet.getBounds();

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 tempEnemy1 = enemy1.get(o);
                formEnemy1 = tempEnemy1.getBounds();

                if (formShoot.intersects(formEnemy1)) {
                    tempEnemy1.setVisible(false);
                    tempBullet.setVisible(false);
                    bullets.remove(j);
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
        }
        player.stop(e);
    }
}
