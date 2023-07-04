package models;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Level extends JPanel implements ActionListener, KeyListener {

    private Image background;
    private Player player;
    private Timer timer;

    private static final int DELAY = 5;
    private static final int WIDTH_WINDOW = 720;

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
    }


    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);

        ArrayList<Bullet> bullets = player.getBullets();
        for (Bullet bullet : bullets) {
            bullet.load();

            graphics.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
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


        repaint();
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
