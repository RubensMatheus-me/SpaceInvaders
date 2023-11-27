package ifpr.paranavai.game.view;

import ifpr.paranavai.game.KeyHandler;
import ifpr.paranavai.game.dao.*;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.enemies.Enemy1;
import ifpr.paranavai.game.models.enemies.MiniMeteor;
import ifpr.paranavai.game.models.scenario.Stars;
import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.models.shoots.SuperShoot;
import ifpr.paranavai.game.service.*;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "tb_level")
public class Level extends JPanel implements ActionListener, KeyListener {
    @Transient
    KeyHandler kh;
    @Transient
    private MenuStyle menuStyle;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @Transient
    private Image background;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_graphic_element")
    private Player player;

    @Transient
    private Timer timer;
    //@OneToMany(mappedBy = "level")
    @Transient
    protected List<ImageIcon> lifes = new ArrayList<>(3);
    @Column(name = "in_game")
    protected boolean inGame;
    @Transient
    private int decay = 2;
    //one to many
    @OneToMany(mappedBy = "level")
    protected List<Enemy1> enemy1 = new ArrayList<Enemy1>();
    //one to many
    @OneToMany(mappedBy = "level")
    protected List<MiniMeteor> miniMeteors = new ArrayList<MiniMeteor>();
    //one to many
    @OneToMany(mappedBy = "level")
    protected List<Stars> stars = new ArrayList<Stars>();
    private static final int DELAY = 5;
    public Menu menu;
    private static final int SCORE_FOR_ENEMIES = 10;

    public int gameState;
    public final int menuState = 0;
    public final int playState = 1;

    public final int pauseState = 2;
    public final int loadState = 3;
    @Transient
    Enemy1 enemy;


    public Level() {
        inGame = false;
        gameState = menuState;
        setFocusable(true);
        setDoubleBuffered(true);
        this.menuStyle = new MenuStyle(this);

        //adicionando a vida ao arraylist
        lifes.add(new ImageIcon(getClass().getResource("/vida.png")));
        lifes.add(new ImageIcon(getClass().getResource("/vida.png")));
        lifes.add(new ImageIcon(getClass().getResource("/vida.png")));

        ImageIcon loader = new ImageIcon(getClass().getResource("/new background.png"));
        background = loader.getImage();
        player = new Player();
        player.load();

        addKeyListener(this);

        timer = new Timer(DELAY, this);
        timer.start();


        //inGame = true;
    }

    public void setupGame() {
        initializeEnemies();
        initializeMiniMeteors();
        initializeStars();

        Timer enemySpawn = new Timer(2000, newActionLister);
        enemySpawn.start();
    }


    public void initializeEnemies() {
        int coord[] = new int[4]; //colocando 4 inimigos de uma vez
        Rectangle rec;
        boolean overlap;

        for (int i = 0; i < coord.length; i++) {
            overlap = false;
            int x = (int) (Math.random() * 1280); //
            int y = (int) (Math.random() * -200 - 150); // altura
            rec = new Rectangle(x, y, 50, 50);

            //método para os inimigos nao nascerem um em cima do outro
            for (int j = 0; j < enemy1.size(); j++) {
                if (enemy1.get(j).getBounds().intersects(rec)) {
                    System.out.println("BOUNDS" + overlap);
                    overlap = true;
                    System.out.println("BOUNDS" + overlap);
                }
            }
            if (!overlap) {
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
    public void pauseScreen(Graphics2D g) {

        String textPause = "PAUSED";
        String textSave = "F5 = SAVEGAME";

        g.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 35));
        g.setColor(new java.awt.Color(255, 255, 255));
        g.drawString(textPause, 450, 300); // x, y
        g.drawString(textSave, 450, 350);

    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if (gameState == menuState) {
            menuStyle.draw(graphics);

        } else {
            if (inGame == true) {
                graphics.drawImage(background, 0, 0, null);
                //setupGame();
                drawningScore(graphics);
                if(gameState == pauseState) {
                    pauseScreen(graphics);
                }

                for (int z = 0; z < miniMeteors.size(); z++) {
                    MiniMeteor s = miniMeteors.get(z);
                    s.load();
                    graphics.drawImage(s.getImage(), s.getPositionX(), s.getPositionY(), this);
                }


                graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);

                for (int i = 0; i < lifes.size(); i++) {
                    graphics.drawImage(lifes.get(i).getImage(), 50 * i, 0, null);
                }


                List<Shoot> shoots = player.getShoots();
                for (Shoot shoot : shoots) {
                    shoot.load();

                    graphics.drawImage(shoot.getImage(), shoot.getPositionX(), shoot.getPositionY(), this);
                }

                List<SuperShoot> superShoots = player.getSuperShoots();
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
            } else {
                ImageIcon gameOver = new ImageIcon(getClass().getResource("/gameover.png"));
                graphics.drawImage(gameOver.getImage(), 0, 0, null);
                inGame = false;
            }

            g.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameState == playState) {
            player.update();

            for (int z = 0; z < miniMeteors.size(); z++) {
                MiniMeteor on = miniMeteors.get(z);
                if (on.isVisible()) {
                    on.update();
                } else {
                    miniMeteors.remove(z);
                }
            }

            for (int s = 0; s < stars.size(); s++) {
                Stars on = stars.get(s);
                if (on.isVisible()) {
                    on.update();
                } else {
                    stars.remove(s);
                }
            }

            List<SuperShoot> superShoots = player.getSuperShoots();
            for (int j = 0; j < superShoots.size(); j++) {

                //removendo os supertiros da tela
                if (superShoots.get(j).getPositionY() < 0) {
                    superShoots.remove(j);
                    System.out.println("tiro removido");
                } else {
                    superShoots.get(j).update();
                }

            }

            List<Shoot> shoots = player.getShoots();
            //removendo os tiros da tela
            for (int i = 0; i < shoots.size(); i++) {
                if (shoots.get(i).getPositionY() < 0) {
                    shoots.remove(i);
                    System.out.println("tiro removido");
                } else {
                    shoots.get(i).update();
                }

            }
            //removendo o inimigo quando passar da tela
            for (int i = 0; i < enemy1.size(); i++) {
                if (enemy1.get(i).getPositionY() > 720) {
                    enemy1.remove(i);
                    System.out.println("inimigo removido");
                } else {
                    enemy1.get(i).update();
                }
            }
            //removendo o mini meteoro quando passar da tela
            for (int i = 0; i < miniMeteors.size(); i++) {
                if (miniMeteors.get(i).getPositionY() > 720) {
                    miniMeteors.remove(i);
                    System.out.println("mini meteoro removido");
                } else {
                    miniMeteors.get(i).update();
                }
            }
            //removendo as estrelas quando passar da tela
            for (int i = 0; i < stars.size(); i++) {
                if (stars.get(i).getPositionY() > 720) {
                    stars.remove(i);
                    System.out.println("esterela removida");
                } else {
                    stars.get(i).update();
                }
            }

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 in = enemy1.get(o);
                if (in.isVisible()) {
                    in.update();
                } else {
                    enemy1.remove(o);
                    System.out.println("inimigo morto");

                }
            }
        }if(gameState == pauseState) {
            //nothing
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
                ImageIcon image = new ImageIcon(getClass().getResource("/semVida.png"));
                lifes.get(decay).setImage(image.getImage());
                decay -= 1;

                //definindo a queda da vida a cada colisao com o inimigo

                player.setLife(player.getLife() - 1);
                if (player.getLife() == 0 || lifes.size() == 0) {
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
                ImageIcon image = new ImageIcon(getClass().getResource("/semVida.png"));
                lifes.get(decay).setImage(image.getImage());
                decay -= 1;

                //definindo a queda da vida a cada colisao com o inimigo
                player.setLife(player.getLife() - 1);
                if (player.getLife() == 0 || lifes.size() == 0) {
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

    public void saveGame() {
        for (Enemy1 enemy11 : getEnemy1()) {
            Enemy1Service.saveOrUpdateEnemy1(enemy11);
        }
        for (MiniMeteor miniMeteor : getMiniMeteors()) {
            MiniMeteorService.saveOrUpdateMiniMeteor(miniMeteor);
        }
        for (Stars stars1 : getStars()) {
            StarsService.saveOrUpdateStars(stars1);
        }

        PlayerService.saveOrUpdatePlayer(getPlayer());
    }
    public void deleteGame() {
        DaoEnemy1 daoEnemy1 = new ImplementDaoEnemy1();
        List<Enemy1> loadEnemy1 = new ImplementDaoEnemy1().searchEnemy();
        if(!loadEnemy1.isEmpty()) {
            for(Enemy1 enemies : loadEnemy1) {
                int enemy1Id = enemies.getIdGraphicElement();
                Enemy1 enemiesSearch = daoEnemy1.searchId(enemy1Id);
                daoEnemy1.delete(enemiesSearch);
            }
        }

        DaoMiniMeteor daoMiniMeteor= new ImplementDaoMiniMeteor();
        List<MiniMeteor> loadMiniMeteors = new ImplementDaoMiniMeteor().searchMiniMeteor();
        if(!loadMiniMeteors.isEmpty()) {
            for (MiniMeteor miniMeteors : loadMiniMeteors) {
                int miniMeteorId = miniMeteors.getIdGraphicElement();
                MiniMeteor miniMeteorSearch = daoMiniMeteor.searchId(miniMeteorId);
                daoMiniMeteor.delete(miniMeteorSearch);
            }
        }

        DaoStars daoStars = new ImplementDaoStars();
        List<Stars> loadStars = new ImplementDaoStars().searchStars();
        if(!loadStars.isEmpty()) {
            for (Stars stars1 : loadStars) {
                int starsId = stars1.getIdGraphicElement();
                Stars starsSearch = daoStars.searchId(starsId);
                daoStars.delete(starsSearch);
            }
        }



    }
    public void loadGame() {
        DaoPlayer daoPlayer = new ImplementDaoPlayer();
        List<Player> loadPlayer = daoPlayer.searchPlayer();

        if(!loadPlayer.isEmpty()) {
            Player player1 = loadPlayer.get(loadPlayer.size() - 1);
            int playerId = player1.getIdGraphicElement();
            setPlayer(player1);
            getPlayer().load();
        }


        DaoEnemy1 daoEnemy1 = new ImplementDaoEnemy1();
        List<Enemy1> loadEnemy1 = new ImplementDaoEnemy1().searchEnemy();
        for(Enemy1 enemies : loadEnemy1) {
            int enemy1Id = enemies.getIdGraphicElement();
            Enemy1 enemiesSearch = daoEnemy1.searchId(enemy1Id);
            getEnemy1().add(enemiesSearch);
            enemies.load();
        }

        DaoMiniMeteor daoMiniMeteor= new ImplementDaoMiniMeteor();
        List<MiniMeteor> loadMiniMeteors = new ImplementDaoMiniMeteor().searchMiniMeteor();
        for(MiniMeteor miniMeteors : loadMiniMeteors) {
            int miniMeteorId = miniMeteors.getIdGraphicElement();
            MiniMeteor miniMeteorSearch = daoMiniMeteor.searchId(miniMeteorId);
            getMiniMeteors().add(miniMeteorSearch);
            miniMeteors.load();
        }

        DaoStars daoStars = new ImplementDaoStars();
        List<Stars> loadStars = new ImplementDaoStars().searchStars();
        for(Stars stars1 : loadStars) {
            int stars1Id = stars1.getIdGraphicElement();
            Stars starsSearch = daoStars.searchId(stars1Id);
            getStars().add(starsSearch);
            stars1.load();
        }

    }

    @Transient
    ActionListener newActionLister = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(inGame == true) {
                if (gameState != pauseState) {
                    initializeEnemies();
                    initializeMiniMeteors();
                    initializeStars();
                }
            }
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
        menuStyle.saveLogic(e);
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_P) {
            if (gameState == playState) {
                gameState = pauseState;

            } else if (gameState == pauseState) {
                gameState = playState;
            }
        }
        menuStyle.menuLogic(e);
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public List<MiniMeteor> getMiniMeteors() {
        return miniMeteors;
    }

    public void setMiniMeteors(List<MiniMeteor> miniMeteors) {
        this.miniMeteors = miniMeteors;
    }

    public List<Stars> getStars() {
        return stars;
    }

    public void setStars(List<Stars> stars) {
        this.stars = stars;
    }

    public List<Enemy1> getEnemy1() {
        return enemy1;
    }

    public void setEnemy1(List<Enemy1> enemy1) {
        this.enemy1 = enemy1;
    }
}