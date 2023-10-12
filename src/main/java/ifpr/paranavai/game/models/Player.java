package ifpr.paranavai.game.models;

import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import javax.persistence.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_player")
public class Player extends GraphicElement {
    @OneToMany(mappedBy = "player")//foreign key
    private List<Shoot> shoots;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "fk_super_shoot") //foreign key
    @OneToMany(mappedBy = "player")
    private List<SuperShoot> superShoots;
    @Column(name = "score")
    private int score;
    @Column(name = "life")
    private int life = 3;

    private static final int DESLOCATION = 5;
    private static final int INITIAL_DESLOCATIONX = 550;
    private static final int INITIAL_DESLOCATIONY = 550;


    public Player() {
        this.positionX = INITIAL_DESLOCATIONX;
        this.positionY = INITIAL_DESLOCATIONY;
        isVisible = true;

        shoots = new ArrayList<Shoot>();
        superShoots = new ArrayList<SuperShoot>();
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/shipflyingstoped.png"));
        this.image = loading.getImage();
        this.height = this.image.getHeight(null);
        this.width = this.image.getWidth(null);
    }
    @Override
    public void update() {
        positionX += deslocationX;
        positionY += deslocationY;

        collisionBorder();
    }



    public void collisionBorder() {
        if (positionX < 0) {
              setPositionX(0);
        } else if (positionX + widthImage > 1220) {
            int maxBorderX = 1220 - widthImage;
            setPositionX(maxBorderX);
        }
        if (positionY < 0) {
            setPositionY(0);
        } else if (getPositionY() + heightImage > 630) {
            int maxBorderY = 630 - heightImage;
            setPositionY(maxBorderY);

        }
    }

    public void simpleBullets() {
        this.shoots.add(new Shoot(positionX + height, positionY + width / 2));
    }

    //movimentar ao apertar a tecla
    public void move(KeyEvent key) {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            this.deslocationY = -DESLOCATION;

        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            this.deslocationY = DESLOCATION;

        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            this.deslocationX = -DESLOCATION;

        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            this.deslocationX = DESLOCATION;

        }
    }

    //parar de movimentar quando soltar a tecla
    public void stop(KeyEvent key) {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            deslocationY = 0;
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            deslocationY = 0;
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            deslocationX = 0;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            deslocationX = 0;
        }
    }

    public void shoot() {
        int frontShip = this.positionX + (this.widthImage / 2);
        int middleShip = this.positionY + (this.heightImage / 2);
        Shoot shoot = new Shoot(frontShip, middleShip);
        this.shoots.add(shoot);
    }
    public void superShoot() {
        int frontShip = this.positionX + (this.widthImage / 2);
        int middleShip = this.positionY + (this.heightImage / 2);
        SuperShoot superShoot;

        // Definir a direção do tiro com base na posição do jogador
        if (deslocationX < 0) {
            superShoot = new SuperShoot(frontShip, middleShip, 1); // Direção: diagonal superior esquerdo
        } else if (deslocationX > 0) {
            superShoot = new SuperShoot(frontShip, middleShip, 2); // Direção: diagonal superior direito
        } else if (deslocationY < 0) {
            superShoot = new SuperShoot(frontShip, middleShip, 3); // Direção: horizontal para a esquerda
        } else {
            superShoot = new SuperShoot(frontShip, middleShip, 4); // Direção: horizontal para a direita
        }

        this.superShoots.add(superShoot);
    }

    public void setShoots(List<Shoot> shoots) {
        this.shoots = shoots;
    }
    public List<Shoot> getShoots() {
        return shoots;
    }

    public List<SuperShoot> getSuperShoots() {
        return superShoots;
    }

    public void setSuperShoots(ArrayList<SuperShoot> superShoots) {
        this.superShoots = superShoots;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
