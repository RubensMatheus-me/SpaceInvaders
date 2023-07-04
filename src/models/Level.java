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
    protected boolean inGame;

    private static final int DELAY = 5;
    private static final int WIDTH_WINDOW = 720;
    protected List<Enemy1> enemy1;

    public Level() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon loader = new ImageIcon("src/resources/maxresdefault.jpg");
        background = loader.getImage();
        player = new Player();
        player.load();

        addKeyListener(this);

        timer = new Timer(DELAY, this);
        timer.start();

        initializeEnemyes();
        inGame = true;
    }
    public void initializeEnemyes() {
        int coord[] = new int [200]; //colocando 40 inimigos
        enemy1 = new ArrayList<Enemy1>();

        for (int i = 0; i < coord.length; i++) {
            int x = (int)(Math.random() * -8000 + 1280);
            int y = (int)(Math.random() * -3000 + 35); // altura
            enemy1.add(new Enemy1(x, y));
        }
    }


    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if (inGame == true) {
            graphics.drawImage(background, 0, 0, null);
            graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);

            ArrayList<Bullet> bullets = player.getBullets();
            for (Bullet bullet : bullets) {
                bullet.load();

                graphics.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 in = enemy1.get(o);
                in.load();
                graphics.drawImage(in.getImage(), in.getX(), in.getY(), this);
            }
        }
        else {
            ImageIcon gameOver = new ImageIcon("src/resources/gamerover.png");
            graphics.drawImage(gameOver.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        ArrayList<Bullet> bullets = player.getBullets();

        for(Bullet bullet : bullets) {
            bullet.update(bullets);
        }
        bullets.removeIf(bullet -> bullet.getX() > WIDTH_WINDOW);

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
                inGame = false;
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
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shoot();

        }else{
            player.move(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.stop(e);
    }
}
